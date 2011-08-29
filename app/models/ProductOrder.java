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
	public float quantity;
	
	@Column("order")
	@Required
	@NotNull
	public Order order;
	
	public ProductOrder(Product product, Member member, float quantity, Order order) {
		this.product = product;
		this.quantity = quantity;
		this.order = order;
	}

	public String quantity() {
		Product product = Product.all().getByKey(this.product.id);
		BaseProduct baseProduct = BaseProduct.all().getByKey(product.baseProduct.id);
		return quantity +  " " + baseProduct.quantityType;
	}
	
	@Override
	public String toString() {
		Product product = Product.all().getByKey(this.product.id);
		return quantity() + " of " + product;
	}
}
