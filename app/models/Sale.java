package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.data.validation.Required;
import siena.Column;
import siena.DateTime;
import siena.Filter;
import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;
import siena.Query;
import utils.DateUtils;
import dto.NewSaleDetailsDto;
import dto.NewSaleDetailsDto.ProductDetailsDto;
import dto.SaleDetailsDto.MemberSaleDetailsDto;

public class Sale extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("date")
	@Required
	@NotNull
	@DateTime
	public Date date;

	@Column("title")
	@Required
	@NotNull
	@Max(100)
	public String title;
	
	@Column("coop")
	@Required
	@NotNull
	public Coop coop;

	@Filter("sale")
	public Query<Product> products;

	@Filter("sale")
	public Query<ProductOrder> productOrders;

	@Filter("sale")
	public Query<ProductPurchase> productPurchases;

	@Filter("sale")
	public Query<MemberSaleDetails> memberDetails;
	
	public Sale(NewSaleDetailsDto details, Coop coop) {
		this.coop = coop;
		this.date = new Date(details.date.getTime());
		this.save();
		List<Product> newProducts = new ArrayList<Product>();
		for (ProductDetailsDto productDetails : details.productDetails) {
			if (productDetails.selected) {
				BaseProduct baseProduct = BaseProduct.getById(productDetails.id);
				Product newProduct = new Product(baseProduct, this, productDetails.currentPrice);
				newProduct.save();
				newProducts.add(newProduct);
			}
		}
	}

	public static Query<Sale> all() {
		return Model.all(Sale.class);
	}

	public static Sale getById(Long id) {
		return all().getByKey(id);
	}

	public static Sale getByTitle(String title) {
		return all().filter("title", title).get();
	}
	
	public static Query<Sale> getByDate(Date date) {
		return all().filter("date", date);
	}

	@Override
	public String toString() {
		return "Date: " + DateUtils.DATE_FORMAT.format(date) + " Coop: "
				+ getCoop();
	}

	public Coop getCoop() {
		return Coop.all().getByKey(coop.id);
	}

	public Query<ProductOrder> getMemberOrders(Member member) {
		return productOrders.filter("member", member);
	}

	public Query<Member> getMembers() {
		 return getCoop().members;
	}

	public Map<Long, Map<Long, ProductOrder>> getOrdersByMember() {
		List<ProductOrder> orders = productOrders.fetch();
		Map<Long, Map<Long, ProductOrder>> memberOrders = new HashMap<Long, Map<Long, ProductOrder>>();
		for (ProductOrder order : orders) {
			Long memberId = order.member.id;
			if (!memberOrders.containsKey(memberId)) {
				memberOrders.put(memberId, new HashMap<Long, ProductOrder>());
			}
			memberOrders.get(memberId).put(order.product.id, order);
		}
		return memberOrders;
	}

	public MemberSaleDetails getMemberDetails(Member member) {
		MemberSaleDetails details = memberDetails.filter("member", member).get();
		if (details == null) {
			details = new MemberSaleDetails(this, member);
		}
		return details;
	}

	public void setDetails(Member member, MemberSaleDetailsDto detailsDto) {
		MemberSaleDetails details = getMemberDetails(member);
		if (details == null) {
			details = new MemberSaleDetails(this, member);
		}
		details.ordered = detailsDto.ordered;
		details.orderTaken = detailsDto.orderTaken;
		details.orderPrice = detailsDto.orderPrice;
		details.payment = detailsDto.payment;
		details.debt = detailsDto.debt;
		details.comment = detailsDto.comment.trim();
		details.save();
	}

	public ProductOrder getMemberProductOrder(Member member, Product product) {
		return productOrders.filter("member", member).filter("product", product).get();
	}

	public List<BaseProduct> baseProducts() {
		List<Product> products = this.products.fetch();
		List<BaseProduct> baseProducts = new ArrayList<BaseProduct>(products.size());
		for (Product product : products) {
			baseProducts.add(BaseProduct.getById(product.baseProduct.id));
		}
		return baseProducts;
	}
}
