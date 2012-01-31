package models;

import java.util.Date;

import play.data.validation.Required;
import siena.Column;
import siena.DateTime;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.NotNull;

public class ProductPurchase extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("date")
	@Required
	@NotNull
	@DateTime
	public Date date;

	@Column("sale")
	@Required
	@NotNull
	public Sale sale;

	@Column("price")
	@Required
	@NotNull
	public float price;

	@Column("supplier")
	@Required
	@NotNull
	public Supplier supplier;

	@Column("base_product")
	@Required
	@NotNull
	public BaseProduct baseProduct;

	@Column("quantity")
	@Required
	@NotNull
	public float quantity;

	@Column("quantity_type")
	@Required
	@NotNull
	public String quantityType;

	public ProductPurchase(Date date, Sale sale, float price,
			Supplier supplier, BaseProduct baseProduct, float quantity) {
		this.date = new Date(date.getTime());
		this.sale = sale;
		this.price = price;
		this.supplier = supplier;
		this.baseProduct = baseProduct;
		this.quantityType = baseProduct.quantityType;
		this.quantity = quantity;
	}
}
