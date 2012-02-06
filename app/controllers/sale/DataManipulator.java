package controllers.sale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import models.Member;
import models.Product;
import models.ProductOrder;

public class DataManipulator {

	public static Set<Member> buildMemberSet(List<ProductOrder> productOrders) {
		Set<Member> members = new TreeSet<Member>();
		for (ProductOrder productOrder : productOrders) {
			Member member = Member.all().getByKey(productOrder.member.id);
			members.add(member);
		}
		return members;
	}

	public static Map<Product, Float> buildTotalsMap(List<ProductOrder> productOrders) {
		Map<Product, Float> totals = new HashMap<Product, Float>();
		for (ProductOrder productOrder : productOrders) {
			Product product = Product.all().getByKey(productOrder.product.id);
			Float total = (totals.containsKey(product) ? totals.get(product) :  0f);
			total += productOrder.quantity;
			totals.put(product, total);
		}
		return totals;
	}

	public static Map<Product, Map<Member, ProductOrder>> buildOrdersMap(
			List<Product> products, List<ProductOrder> productOrders) {
		Map<Product, Map<Member, ProductOrder>> ordersMap = new HashMap<Product, Map<Member, ProductOrder>>();
		for (Product product : products) {
			ordersMap.put(product, new HashMap<Member, ProductOrder>());
		}
		for (ProductOrder productOrder : productOrders) {
			Member member = Member.all().getByKey(productOrder.member.id);
			Product product = Product.all().getByKey(
					productOrder.product.id);
			ordersMap.get(product).put(member, productOrder);
		}
		return ordersMap;
	}

	public static float calcMemberTotalOrder(long memberId, List<ProductOrder> orders) {
		float total = 0;
		for (ProductOrder order : orders) {
			if (order.member.id == memberId) {
				order.product.get();
				total += order.quantity * order.product.price;
			}
		}
		return total;
	}
}
