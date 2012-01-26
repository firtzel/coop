package controllers.inventory;

import java.util.Map;
import java.util.Set;

import models.BaseProduct;
import models.Sale;

public class InventoryResult {
	private final Set<BaseProduct> baseProducts;
	private final Map<Sale, Map<BaseProduct, Inventory>> inventory;

	public InventoryResult(Set<BaseProduct> baseProducts,
			Map<Sale, Map<BaseProduct, Inventory>> inventory) {
		this.baseProducts = baseProducts;
		this.inventory = inventory;
	}

	public Set<BaseProduct> getBaseProducts() {
		return baseProducts;
	}

	public Map<Sale, Map<BaseProduct, Inventory>> getInventory() {
		return inventory;
	}
}
