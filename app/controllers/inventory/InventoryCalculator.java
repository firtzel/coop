package controllers.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import models.BaseProduct;
import models.Product;
import models.ProductInventory;
import models.ProductOrder;
import models.ProductPurchase;
import models.Sale;

public class InventoryCalculator {
	private final List<Sale> sales;

	public InventoryCalculator(List<Sale> sales) {
		this.sales = sales;
	}

	public InventoryResult calculate() {
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
		return new InventoryResult(baseProducts, inventory);
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
}
