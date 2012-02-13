package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.BaseProduct;
import models.Member;
import models.MemberSaleDetails;
import models.Product;
import models.ProductOrder;
import models.Sale;
import play.Logger;

import com.google.gson.Gson;

import controllers.response.SuccessResponse;
import controllers.sale.DataManipulator;
import dto.SaleDetailsDto;
import dto.SaleDetailsDto.MemberSaleDetailsDto;

public class Sales extends ConnectedController {

	private static Sale getById(Long id) {
		return (id != null ? Sale.getById(id) : getCoop().latestSale());
	}

	public static void myOrders(Long id) {
		Sale sale = getById(id);
		List<Product> products = sale.products.fetch();
		List<ProductOrder> productOrders = sale.getMemberOrders(getMember()).fetch();
		renderTemplate("sales/my_orders.html", sale, products, productOrders);
	}

	public static void edit(Long id) {
		Sale sale = Sale.getById(id);
		Member member = getMember();
		Map<Product, String> productTypes = new HashMap<Product, String>();
		Map<Product, Float> productQuantities = new HashMap<Product, Float>();
		for (Product product : sale.products.fetch()) {
			BaseProduct baseProduct = BaseProduct.all().getByKey(product.baseProduct.id);
			productTypes.put(product, baseProduct.quantityType);
			ProductOrder productOrder = sale.getMemberProductOrder(member, product);
			productQuantities.put(product, (productOrder == null ? 0f : productOrder.quantity));
		}
		render(sale, productTypes, productQuantities);
	}

	public static void save(Long id) {
		Sale sale = Sale.getById(id);
		Member member = getMember();
		List<Product> products = sale.products.fetch();
		for (Product product : products) {
			String key = "quantity-" + product.id;
			float quantity = Float.parseFloat(params.get(key));
			ProductOrder productOrder = sale.getMemberProductOrder(member, product);
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
		Sale sale = getById(id);
		List<ProductOrder> productOrders = sale.productOrders.fetch();
		List<Product> products = sale.products.fetch();
		Set<Member> members = DataManipulator.buildMemberSet(productOrders);
		Map<Product, Float> totals = DataManipulator.buildTotalsMap(productOrders);
		Map<Product, Map<Member, ProductOrder>> ordersMap = DataManipulator.buildOrdersMap(
				products, productOrders);
		renderTemplate("sales/all_orders.html", sale, ordersMap, members, totals);
	}

	public static void manage(Long id) {
		Sale sale = getById(id);
		List<Member> members = sale.coop.members.fetch();
		render(sale, members);
	}

	// TODO remove duplicate code
	public static void manageJson(Long id) {
		Sale sale = Sale.getById(id);
		List<Member> members = sale.coop.members.fetch();
		List<ProductOrder> productOrders = sale.productOrders.fetch();
		Set<Member> orderingMembers = DataManipulator.buildMemberSet(productOrders);
		SaleDetailsDto saleDetails = new SaleDetailsDto();
		for (Member member : members) {
			// TODO move the "ordered" and "memberOrders" calculation elsewhere
			boolean ordered = orderingMembers.contains(member);
			float memberOrders = (ordered ? DataManipulator.calcMemberTotalOrder(member.id, productOrders) : 0);
			MemberSaleDetails details = sale.getMemberDetails(member);
			MemberSaleDetailsDto saleMemberDetails = new MemberSaleDetailsDto(member, ordered, memberOrders, details);
			saleDetails.addMemberDetails(saleMemberDetails);
		}
		renderJSON(saleDetails);
	}

	public static void manageSave(Long id) {
		Sale sale = Sale.getById(id);
		String data = params.get("data");
		SaleDetailsDto saleDetails = new Gson().fromJson(data, SaleDetailsDto.class);
		for (MemberSaleDetailsDto details : saleDetails.getMemberDetails()) {
			Member member = Member.all().getByKey(details.id);
			sale.setDetails(member, details);
		}
		renderJSON(new SuccessResponse("Sale saved successfully"));
	}
}
