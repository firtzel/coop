package models;

import play.data.validation.Required;
import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;
import utils.Quantity;

public class ProductOrder extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("product")
	@Required
	@NotNull
	public Product product;

	@Column("member")
	@Required
	@NotNull
	public Member member;

	@Column("sale")
	@Required
	@NotNull
	public Sale sale;

	@Column("quantity")
	@Required
	@NotNull
	public float quantity;
	
	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;

	public ProductOrder(Product product, Member member, Sale sale, float quantity) {
		this.product = product;
		this.member = member;
		this.sale = sale;
		this.quantity = quantity;
		this.quantityType = product.quantityType;
	}

	public String quantity() {
		return quantity +  " " + quantityType;
	}
	
	@Override
	public String toString() {
		Product product = Product.all().getByKey(this.product.id);
		return quantity() + " of " + product;
	}
}
