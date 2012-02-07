package models;

import java.util.Date;

import com.google.appengine.repackaged.com.google.common.base.Objects;

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

	@Column("group")
	@Required
	@NotNull
	public CoopGroup group;

//	public BaseProduct(String name, float price, float inventory, String quantityType) {
//		this.name = name;
//		this.price = price;
//		this.inventory = inventory;
//		this.quantityType = quantityType;
//	}

	public static Query<BaseProduct> all() {
		return Model.all(BaseProduct.class);
	}

	public static BaseProduct getById(Long id) {
		return all().getByKey(id);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(BaseProduct other) {
		return id.compareTo(other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name);
	}

	@Override
	public boolean equals(Object that) {
		if (that instanceof BaseProduct) {
			BaseProduct other = (BaseProduct) that;
			return Objects.equal(id, other.id) &&
					Objects.equal(name, other.name);
		} else {
			return false;
		}
	}
}
