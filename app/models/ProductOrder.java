package models;

import java.math.BigDecimal;

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

	@Column("quantity")
	@Required
	@NotNull
	@Max(30)
	public String quantity;
	
	@Column("order")
	@Required
	@NotNull
	public Order order;
	
	public ProductOrder(Product product, Member member, Quantity quantity, Order order) {
		this.product = product;
		this.quantity = quantity.toString();
		this.order = order;
	}

	@Override
	public String toString() {
		return quantity + " of " + Product.all().getByKey(product.id);
	}
}
