package models;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;

public class Supplier extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("name")
	@Required
	@Max(50)
	@NotNull
	public String name;

	public Supplier(String name) {
		this.name = name;
	}
}
