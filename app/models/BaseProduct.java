package models;

import java.util.Date;

import play.data.validation.Required;
import siena.*;
import utils.QuantityType;

public class BaseProduct extends Model implements Comparable<BaseProduct> {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("name")
	@Required
	@NotNull
	@Max(100)
	public String name;

	@Column("inventory")
	@Required
	@NotNull
	public float inventory;

	// TODO see if we can use QuantityType type here instead of String 
	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;

	@Column("price")
	@Required
	@NotNull
	public float price;

	public BaseProduct(String name, float price, float inventory, String quantityType) {
		this.name = name;
		this.price = price;
		this.inventory = inventory;
		this.quantityType = quantityType;
	}

	public static Query<BaseProduct> all() {
		return Model.all(BaseProduct.class);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(BaseProduct other) {
		return id.compareTo(other.id);
	}
}
