package models;

import static utils.DateUtils.DATE_FORMAT;

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

public class Order extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("date")
	@Required
	@NotNull
	@DateTime
	public Date date;

	@Column("member")
	@Required
	@NotNull
	public Member member;

	@Column("sale")
	@Required
	@NotNull
	public Sale sale;

	@Filter("order")
	public Query<ProductOrder> products;

	public Order(Date date, Member member, Sale sale) {
		this.date = new Date(date.getTime());
		this.member = member;
		this.sale = sale;
	}

	public static Query<Order> all() {
		return Model.all(Order.class);
	}

	public static Query<Order> getBySale(Sale sale) {
		return all().filter("sale", sale);
	}

	public static Query<Order> getByDate(Date date) {
		return all().filter("date", date);
	}

	@Override
	public String toString() {
		return "Ordered by " + Member.all().getByKey(member.id) + " at "
				+ DATE_FORMAT.format(date) + " as part of "
				+ Sale.all().getByKey(sale.id);
	}

//	@Override
//	public void save() {
//		for (ProductOrder productOrder : products.fetch()) {
//			assert productOrder.product.sale.equals(this.sale) : "Product " + productOrder + " is not part of the same sale as order " + this;
//		}
//		super.save();
//	}
}
