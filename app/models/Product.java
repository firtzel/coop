package models;

import java.util.List;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Max;
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

	// inventory - the product quantity, excluding purchases from suppliers and members orders
	@Column("inventory")
	@Required
	@NotNull
	public float inventory;

	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;

	public Product(BaseProduct baseProduct, Sale sale, float price) {
		this.baseProduct = baseProduct;
		this.sale = sale;
		this.price = price;
		this.inventory = baseProduct.inventory;
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
