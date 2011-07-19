package models;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.NotNull;
import siena.Query;

public class Product extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("base_product")
	@Required
	@NotNull
	public BaseProduct baseProduct;

	@Column("sale")
	@Required
	@NotNull
	public Sale sale;

	// TODO add more information, such as: price, quantity, etc.

	public Product(BaseProduct baseProduct, Sale sale) {
		this.baseProduct = baseProduct;
		this.sale = sale;
	}

	public static Query<Product> all() {
		return Model.all(Product.class);
	}

	@Override
	public String toString() {
		return BaseProduct.all().getByKey(baseProduct.id).toString();
	}
}
