package models;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;

public class MemberSaleDetails extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("member")
	@Required
	@NotNull
	public Member member;

	@Column("sale")
	@Required
	@NotNull
	public Sale sale;

	@Column("ordered")
	@Required
	@NotNull
	public Boolean ordered = false;

	@Column("order_taken")
	@Required
	@NotNull
	public Boolean orderTaken = false;
	
	@Column("debt")
	@Required
	@NotNull
	public Float orderPrice = 0f;

	@Column("payment")
	@Required
	@NotNull
	public Float payment = 0f;
	
	@Column("debt")
	@Required
	@NotNull
	public Float debt = 0f;

	@Column("comment")
	@Required
	@NotNull
	@Max(value = 200)
	public String comment = "";

	public MemberSaleDetails(Sale sale, Member member) {
		this.sale = sale;
		this.member = member;
	}

//	public MemberSaleDetails(Member member, Sale sale, Boolean ordered, Boolean orderTaken,
//			Float orderPrice, Float payment, Float debt, String comment) {
//		this.member = member;
//		this.sale = sale;
//		this.ordered = ordered;
//		this.orderTaken = orderTaken;
//		this.orderPrice = orderPrice;
//		this.payment = payment;
//		this.debt = debt;
//		this.comment = comment;
//	}
}
