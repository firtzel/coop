package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.data.validation.Required;
import siena.Column;
import siena.DateTime;
import siena.Filter;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.NotNull;
import siena.Query;

public class Sale extends Model {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	// public enum Type { // TODO make this configurable
	// DRY, FRESH
	// }

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("date")
	@Required
	@NotNull
	@DateTime
	public Date date;

	@Column("coop")
	@Required
	@NotNull
	public Coop coop;

	@Filter("sale")
	public Query<Product> products;

	public Sale(Date date, Coop coop) {
		this.date = new Date(date.getTime());
		this.coop = coop;
	}

	public static Query<Sale> all() {
		return Model.all(Sale.class);
	}

	@Override
	public String toString() {
		return "Date: " + DATE_FORMAT.format(date) + " Coop: "
				+ coop.all().getByKey(coop.id);
	}
}
