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

	@Column("price")
	@Required
	@NotNull
	public float price;

	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;

	// TODO add more information, such as: price, quantity, etc.
	
	public Product(BaseProduct baseProduct, Sale sale, float price) {
		this.baseProduct = baseProduct;
		this.sale = sale;
		this.price = price;
		this.quantityType = baseProduct.quantityType;
	}

	public static Query<Product> all() {
		return Model.all(Product.class);
	}

	@Override
	public String toString() {
		return BaseProduct.all().getByKey(baseProduct.id).toString();
	}
}
