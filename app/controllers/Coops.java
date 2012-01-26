package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import controllers.inventory.Inventory;
import controllers.inventory.InventoryCalculator;
import controllers.inventory.InventoryResult;

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
