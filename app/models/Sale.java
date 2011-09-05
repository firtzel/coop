package models;

import java.util.Date;
import java.util.TimeZone;

import play.data.validation.Required;
import siena.Column;
import siena.DateTime;
import siena.Filter;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.NotNull;
import siena.Query;
import utils.DateUtils;

public class Sale extends Model {

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

	@Filter("sale")
	public Query<ProductOrder> productOrders; // TODO add test for this

	@Filter("sale")
	public Query<ProductPurchase> productPurchases; // TODO add test for this

	public Sale(Date date, Coop coop) {
		this.date = new Date(date.getTime());
		this.coop = coop;
	}

	public static Query<Sale> all() {
		return Model.all(Sale.class);
	}

	public static Query<Sale> getByDate(Date date) {
		return all().filter("date", date);
	}

	@Override
	public String toString() {
		return "Date: " + DateUtils.DATE_FORMAT.format(date) + " Coop: "
				+ Coop.all().getByKey(coop.id);
	}
}
