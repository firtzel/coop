package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.sun.istack.internal.Nullable;

import play.data.validation.Required;
import siena.Column;
import siena.Filter;
import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;
import siena.Query;
import siena.Table;

@Table("coop_group")
public class CoopGroup extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("title")
	@Required
	@Max(50)
	@NotNull
	public String title;

	@Column("description")
	@Required
	@Max(500)
	@NotNull
	public String description;

	@Filter("group")
	public Query<Coop> coops;

	public CoopGroup(String title, String description) {
		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return title;
	}

	public static Query<CoopGroup> all() {
		return Model.all(CoopGroup.class);
	}
}
