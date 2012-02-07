package controllers.dto;

import java.util.ArrayList;
import java.util.List;

import models.BaseProduct;
import models.Coop;
import models.CoopGroup;

public class NewSaleDetailsDto {
	public List<ProductDetailsDto> productDetails;

	public NewSaleDetailsDto(Coop coop) {
		productDetails = new ArrayList<NewSaleDetailsDto.ProductDetailsDto>();
		CoopGroup group = CoopGroup.all().getByKey(coop.group.id);
		for (BaseProduct product : group.baseProducts.fetch()) {
			productDetails.add(new ProductDetailsDto(false, product.id, product.name, product.price));
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
