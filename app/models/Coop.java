package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

	@Filter("coop")
	public Query<Member> members;

	public Coop(String title, String description, @Nullable CoopGroup group) {
		this.title = title;
		this.description = description;
		this.group = group;
	}

	public static Query<Coop> all() {
		return Model.all(Coop.class);
	}

	public static Coop getById(Long id) {
		return all().getByKey(id);
	}

	public static Coop getByTitle(String title) {
		return all().filter("title", title).get();
	}

	public Sale latestSale() {
		return sales.order("-date").get();
	}

	@Override
	public String toString() {
		if (group == null) {
			return title;
		} else {
			return title + " (part of "
					+ CoopGroup.all().getByKey(group.id).toString() + ')';
		}
	}

	public static Collection<Coop> findByMember(Collection<Member> members) {
		Collection<Coop> coops = new ArrayList<Coop>(members.size());
		for (Member member : members) {
			coops.add(getById(member.coop.id));
		}
		return coops;
	}
}
