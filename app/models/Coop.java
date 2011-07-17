package models;

import javax.persistence.*;

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

import com.sun.istack.internal.Nullable;

@Table("coops")
public class Coop extends Model {

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

	@Column("group")
	@Nullable
	public CoopGroup group;

	@Filter("coop")
	public Query<Sale> sales;

	public Coop(String title, String description, @Nullable CoopGroup group) {
		this.title = title;
		this.description = description;
		this.group = group;
	}

	public static Query<Coop> all() {
		return Model.all(Coop.class);
	}

	@Override
	public String toString() {
		if (group == null) {
			return title;
		} else {
			return title + " (part of " + CoopGroup.all().getByKey(group.id).toString() + ')';
		}
	}
}
