package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import models.Coop;
import models.Order;
import models.Product;
import models.Member;
import models.ProductOrder;
import models.Sale;
import models.User;
import play.modules.gae.GAE;
import play.mvc.Before;

public class Sales extends ConnectedController {

	public static void details(Long id) {
		Sale sale = Sale.all().getByKey(id);
		Order order = Order.all().filter("member", getMember())
				.filter("sale", sale).get();
		render(sale, order);
	}

	public static void total(Long id) {
		Sale sale = Sale.all().getByKey(id);
		List<Order> orders = Order.all().filter("sale", sale).fetch();
		List<Product> products = sale.products.fetch();
		Set<Member> members = new TreeSet<Member>();
		Map<Product, Map<Member, ProductOrder>> ordersMap = buildOrdersMap(
				products, orders, members);
		render(sale, ordersMap, members);
	}

	private static Map<Product, Map<Member, ProductOrder>> buildOrdersMap(
			List<Product> products, List<Order> orders, Set<Member> members) {
		Map<Product, Map<Member, ProductOrder>> ordersMap = new HashMap<Product, Map<Member, ProductOrder>>();
		for (Product product : products) {
			ordersMap.put(product, new HashMap<Member, ProductOrder>());
		}
		for (Order order : orders) {
			Member member = Member.all().getByKey(order.member.id);
			members.add(member);
			for (ProductOrder productOrder : order.products.fetch()) {
				Product product = Product.all().getByKey(
						productOrder.product.id);
				ordersMap.get(product).put(member, productOrder);
			}
		}
		return ordersMap;
	}
}
