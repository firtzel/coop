package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import siena.Query;

import models.BaseProduct;
import models.Coop;
import models.Member;
import models.Product;
import models.ProductInventory;
import models.ProductOrder;
import models.ProductPurchase;
import models.Sale;

public class Coops extends ConnectedController {

	public static void list() {
		Collection<Member> members = Member.findByUser(getUser());
		Collection<Coop> coops = Coop.findByMember(members);
		render(coops);
	}

	private static class Inventory {
		public final float purchases;
		public final float orders;
		public final float current;

		public Inventory(float currInventory, float totalPurchases, float totalOrders) {
			current = currInventory;
			purchases = totalPurchases;
			orders = totalOrders;
		}

		@Override
		public String toString() {
			return "current: " +  current + " purchases: " + purchases + " orders: " + orders;
		}
	}

	public static void inventory(Long id) {
		Coop coop = Coop.all().getByKey(id);
		List<Sale> sales = coop.sales.order("date").fetch();
		SortedSet<BaseProduct> baseProducts = new TreeSet<BaseProduct>();
		Map<Sale, Map<BaseProduct, Inventory>> inventory = new HashMap<Sale, Map<BaseProduct, Inventory>>();
		for (Sale sale : sales) {
			Map<BaseProduct, Inventory> saleInventory = new HashMap<BaseProduct, Inventory>();
			List<Product> saleProducts = sale.products.fetch();
			for (Product product : saleProducts) {
				BaseProduct baseProduct = BaseProduct.all().getByKey(product.baseProduct.id);
				float totalPurchases = calcTotalPurchases(sale, product);
				float totalOrders = calcTotalOrders(sale, product);
				float currInventory = getProductInventory(product);
				// Show: (product inventory) + currProductPurchases.quantity - productOrders.quantity 
				baseProducts.add(baseProduct);
				saleInventory.put(baseProduct, new Inventory(currInventory, totalPurchases, totalOrders));
			}
			inventory.put(sale, saleInventory);
		}
		render(coop, sales, baseProducts, inventory);
	}
	
	private static float calcTotalOrders(Sale sale, Product product) {
		List<ProductOrder> productOrders = sale.productOrders.filter("product", product).fetch();
		float total = 0;
		for (ProductOrder productOrder : productOrders) {
			total += productOrder.quantity;
		}
		return total;
	}

	private static float calcTotalPurchases(Sale sale, Product product) {
		List<ProductPurchase> productPurchases = sale.productPurchases.filter("product", product).fetch();
		float total = 0;
		for (ProductPurchase productPurchase : productPurchases) {
			total += productPurchase.quantity;
		}
		return total;
	}

	private static float getProductInventory(Product product) {
		ProductInventory productInventory = ProductInventory.all().filter("product", product).get();
		return (productInventory != null ? productInventory.quantity : product.inventory);
	}

	public static void details(Long id) {
		Coop coop = Coop.all().getByKey(id);
		render(coop);
	}
}
