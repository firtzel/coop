package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import models.BaseProduct;
import models.Coop;
import models.Product;
import models.Member;
import models.ProductOrder;
import models.Sale;
import models.User;

import play.Logger;
import play.modules.gae.GAE;
import play.mvc.Before;

import siena.Query;

public class Sales extends ConnectedController {

	public static void myOrders(Long id) {
		Sale sale = Sale.all().getByKey(id);
		List<Product> products = sale.products.fetch();
		List<ProductOrder> productOrders = sale.getMemberOrders(getMember()).fetch();
		renderTemplate("sales/my_orders.html", sale, products, productOrders);
	}

	public static void edit(Long id) {
		Sale sale = Sale.all().getByKey(id);
		Map<Product, String> productTypes = new HashMap<Product, String>();
		Map<Product, Float> productQuantities = new HashMap<Product, Float>();
		for (Product product : sale.products.fetch()) {
			BaseProduct baseProduct = BaseProduct.all().getByKey(product.baseProduct.id);
			productTypes.put(product, baseProduct.quantityType);
			ProductOrder productOrder = sale.productOrders.filter("member", getMember()).filter("product", product).get();
			productQuantities.put(product, (productOrder == null ? 0f : productOrder.quantity));
		}
		render(sale, productTypes, productQuantities);
	}

	public static void save(Long id) {
		Sale sale = Sale.all().getByKey(id);
		Member member = getMember();
		List<Product> products = sale.products.fetch();
		for (Product product : products) {
			String key = "quantity-" + product.id;
			float quantity = Float.parseFloat(params.get(key)); // TODO break this to pieces
			// TODO move this query to a method (repeats twice)
			ProductOrder productOrder = sale.productOrders.filter("member", getMember()).filter("product", product).get();
			if (quantity != 0f) {
				Logger.info("Ordered quantity " + quantity + " of product " + product);
				if (productOrder == null) {
					productOrder = new ProductOrder(product, member, sale, quantity);
				} else if (productOrder.quantity != quantity) {
					Logger.info("Updating amount of " + product);
					productOrder.quantity = quantity;
				}
				productOrder.save();
			} else { // zero quantity is ordered - remove order if exists
				Logger.info("Ordered none of product " + product);
				if (productOrder != null) {
					Logger.info("deleting order of product " + product);
					productOrder.delete();
				}
			}
		}
		myOrders(id);
	}

	public static void allOrders(Long id) {
		Sale sale = Sale.all().getByKey(id);
		List<ProductOrder> productOrders = sale.productOrders.fetch();
		List<Product> products = sale.products.fetch();
		Set<Member> members = new TreeSet<Member>();
		Map<Product, Float> totals = new HashMap<Product, Float>();
		Map<Product, Map<Member, ProductOrder>> ordersMap = buildOrdersMap(
				products, productOrders, members, totals);
		renderTemplate("sales/all_orders.html", sale, ordersMap, members, totals);
	}

	private static Map<Product, Map<Member, ProductOrder>> buildOrdersMap(
			List<Product> products, List<ProductOrder> productOrders, 
			Set<Member> members, Map<Product, Float> totals) {
		Map<Product, Map<Member, ProductOrder>> ordersMap = new HashMap<Product, Map<Member, ProductOrder>>();
		for (Product product : products) {
			ordersMap.put(product, new HashMap<Member, ProductOrder>());
		}
		for (ProductOrder productOrder : productOrders) {
			Member member = Member.all().getByKey(productOrder.member.id);
			members.add(member);
			Product product = Product.all().getByKey(
					productOrder.product.id);
			ordersMap.get(product).put(member, productOrder);
			Float total = (totals.containsKey(product) ? totals.get(product) :  0f);
			total += productOrder.quantity;
			totals.put(product, total);
		}
		return ordersMap;
	}
}
