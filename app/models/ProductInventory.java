package models;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.NotNull;
import siena.Query;

// this model is used to override the automatically-generated product.inventory field in the Product model
public class ProductInventory extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("product")
	@Required
	@NotNull
	public Product product;

	@Column("quantity")
	@Required
	@NotNull
	public float quantity;
	
	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;
	
	public ProductInventory(Product product, float quantity) {
		this.product = product;
		this.quantityType = product.quantityType;
		this.quantity = quantity;
	}

	public static Query<ProductInventory> all() {
		return Model.all(ProductInventory.class);
	}
}
