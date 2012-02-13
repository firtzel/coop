package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import siena.Query;

import models.BaseProduct;
import models.Coop;
import models.CoopGroup;
import models.Product;
import models.Sale;

public class NewSaleDetailsDto {
	public final Date date;
	public final List<ProductDetailsDto> productDetails;

	public NewSaleDetailsDto(Coop coop, Sale sale) {
		productDetails = new ArrayList<NewSaleDetailsDto.ProductDetailsDto>();
		CoopGroup group = CoopGroup.all().getByKey(coop.group.id);
		List<BaseProduct> saleBaseProducts;
		if (sale != null) {
			date = sale.date;
			saleBaseProducts = sale.baseProducts();
		} else {
			date = new Date();
			saleBaseProducts = Collections.emptyList();
		}
		for (BaseProduct product : group.baseProducts.fetch()) {
			boolean selected = saleBaseProducts.contains(product);
			productDetails.add(new ProductDetailsDto(selected, product.id,
					product.name, product.price));
		}
	}

	public static class ProductDetailsDto {
		public final boolean selected;
		public final long id;
		public final String name;
		public final float latestPrice;
		public final float currentPrice;

		public ProductDetailsDto(boolean selected, long id, String name,
				float price) {
			this.selected = selected;
			this.id = id;
			this.name = name;
			this.latestPrice = price;
			this.currentPrice = price;
		}
	}
}
