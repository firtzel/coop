package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import siena.Query;

import models.BaseProduct;
import models.Coop;
import models.CoopGroup;
import models.Product;
import models.Sale;

public class NewSaleDetailsDto {
	public List<ProductDetailsDto> productDetails;

	public NewSaleDetailsDto(Coop coop, Sale sale) {
		productDetails = new ArrayList<NewSaleDetailsDto.ProductDetailsDto>();
		CoopGroup group = CoopGroup.all().getByKey(coop.group.id);
		List<BaseProduct> saleBaseProducts;
		if (sale != null) {
			saleBaseProducts = sale.baseProducts();
		} else {
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
		public final float price;

		public ProductDetailsDto(boolean selected, long id, String name,
				float price) {
			this.selected = selected;
			this.id = id;
			this.name = name;
			this.price = price;
		}
	}
}
