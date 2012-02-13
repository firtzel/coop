package controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import models.BaseProduct;
import models.Coop;
import models.Member;
import models.Sale;
import controllers.inventory.Inventory;
import controllers.inventory.InventoryCalculator;
import controllers.inventory.InventoryResult;
import controllers.response.SuccessResponse;
import dto.NewSaleDetailsDto;

public class Coops extends ConnectedController {

//	public static void list() {
//		Collection<Member> members = Member.findByUser(getUser());
//		Collection<Coop> coops = Coop.findByMember(members);
//		render(coops);
//	}

	private static Coop getById(Long id) {
		return (id == null ? getCoop() : Coop.getById(id));
	}

	public static void inventory(Long id) {
		Coop coop = getById(id);
		List<Sale> sales = coop.sales.order("date").fetch();
		InventoryCalculator inventoryCalculator = new InventoryCalculator(sales);
		InventoryResult inventoryResult = inventoryCalculator.calculate();
		Set<BaseProduct> baseProducts = inventoryResult.getBaseProducts();
		Map<Sale, Map<BaseProduct, Inventory>> inventory = inventoryResult.getInventory();
		render(coop, sales, baseProducts, inventory);
	}
	
	public static void details(Long id) {
		Coop coop = getById(id);
		render(coop);
	}

	public static void newSale(Long id) {
		Coop coop = getById(id);
		Long saleId = params.get("sale", Long.class);
		render("coops/new_sale.html", coop, saleId);
	}

	public static void newSaleJson(Long id) {
		Coop coop = getById(id);
		Sale sale;
		Long saleId = params.get("sale", Long.class);
		if (saleId == null) {
			sale = coop.latestSale();
		} else {
			sale = Sale.getById(Long.parseLong(params.get("sale")));
		}
		NewSaleDetailsDto details = new NewSaleDetailsDto(coop, sale);
		renderJSON(details);
	}

	public static void saveSale(Long id) {
		Coop coop = getById(id);
		NewSaleDetailsDto details = new Gson().fromJson(params.get("data"), NewSaleDetailsDto.class);
		// TODO create new sale here
		renderJSON(new SuccessResponse("New sale created successfully"));
	}
}
