package models;

import java.util.Date;

import play.data.validation.Required;
import siena.*;

public class BaseProduct extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("name")
	@Required
	@NotNull
	@Max(100)
	public String name;

	public BaseProduct(String name) {
		this.name = name;
	}

	public static Query<BaseProduct> all() {
		return Model.all(BaseProduct.class);
	}

	@Override
	public String toString() {
		return name;
	}
}
