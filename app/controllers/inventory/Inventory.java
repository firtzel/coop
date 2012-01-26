package controllers.inventory;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class Inventory {
	public final float purchases;
	public final float orders;
	public final float current;

	public Inventory(float currInventory, float totalPurchases,
			float totalOrders) {
		current = currInventory;
		purchases = totalPurchases;
		orders = totalOrders;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("current", current)
				.add("purchases", purchases).add("orders", orders).toString();
	}
}
