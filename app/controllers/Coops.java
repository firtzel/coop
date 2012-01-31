package controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.BaseProduct;
import models.Coop;
import models.Member;
import models.Sale;
import controllers.inventory.Inventory;
import controllers.inventory.InventoryCalculator;
import controllers.inventory.InventoryResult;

public class Coops extends ConnectedController {

	public static void list() {
		Collection<Member> members = Member.findByUser(getUser());
		Collection<Coop> coops = Coop.findByMember(members);
		render(coops);
	}

	public static void inventory(Long id) {
		Coop coop = Coop.all().getByKey(id);
		List<Sale> sales = coop.sales.order("date").fetch();
		InventoryCalculator inventoryCalculator = new InventoryCalculator(sales);
		InventoryResult inventoryResult = inventoryCalculator.calculate();
		Set<BaseProduct> baseProducts = inventoryResult.getBaseProducts();
		Map<Sale, Map<BaseProduct, Inventory>> inventory = inventoryResult.getInventory();
		render(coop, sales, baseProducts, inventory);
	}
	
	public static void details(Long id) {
		Coop coop = Coop.all().getByKey(id);
		render(coop);
	}
}
