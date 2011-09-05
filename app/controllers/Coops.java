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
import models.ProductPurchase;
import models.Sale;

public class Coops extends ConnectedController {

	public static void list() {
//		List<Coop> coops = Coop.all().fetch();
//		render(coops);
		Collection<Member> members = Member.findByUser(getUser());
		Collection<Coop> coops = Coop.findByMember(members);
		render(coops);
	}

	public static void inventory(Long id) {
		Coop coop = Coop.all().getByKey(id);
		List<Sale> sales = coop.sales.order("date").fetch();
		SortedSet<BaseProduct> baseProducts = new TreeSet<BaseProduct>();
		Map<Sale, Map<BaseProduct, ProductInventory>> inventory = new HashMap<Sale, Map<BaseProduct,ProductInventory>>();
		Map<BaseProduct, ProductInventory> prevSaleInventory = null;
		for (Sale sale : sales) {
			Map<BaseProduct, ProductInventory> saleInventory = new HashMap<BaseProduct, ProductInventory>(); 
			List<Product> saleProducts = sale.products.fetch();
			Query<ProductPurchase> productPurchases = sale.productPurchases;
//			sale.orders
			for (Product product : saleProducts) {
				BaseProduct baseProduct = BaseProduct.all().getByKey(product.baseProduct.id);
				List<ProductPurchase> currProductPurchases = productPurchases.filter("product", product.id).fetch(); 
//				product.inventory + currProductPurchases.quantity - 
//				ProductInventory productInventory = sal
				baseProducts.add(baseProduct);
				ProductInventory prevProductInventory = prevSaleInventory.get(baseProduct);
//				ProductInventory currProductInventory = 
			}
			inventory.put(sale, saleInventory);
			prevSaleInventory = saleInventory;
		}
		render(coop, sales, baseProducts, inventory);
	}
	
	public static void details(Long id) {
		Coop coop = Coop.all().getByKey(id);
		render(coop);
	}
}
