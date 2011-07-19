package models;

import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.test.UnitTest;
import siena.Query;

public class SaleTest extends UnitTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("coops.yml", "sales.yml");
	}

	@Test
	public void count() {
		assertEquals(2, Sale.all().count());
		assertEquals(3, BaseProduct.all().count());
		assertEquals(2, Product.all().count());
	}

	@Test
	public void getEmptySale() {
		Sale empty = Sale.all().fetch().get(0);
		assertEquals("Date: 2011-02-01 Coop: first (part of group)", empty.toString());
		assertEquals(0, empty.products.count());
	}

	@Test
	public void getNoneEmptySale() {
		Sale sale = Sale.all().fetch().get(1);
		assertEquals("Date: 2011-02-08 Coop: first (part of group)", sale.toString());
		assertEquals(2, sale.products.count());
	}

//	@Test
//	public void getGroup() {
//		CoopGroup group = CoopGroup.all().filter("title", "group").get();
//		assertEquals("group", group.title);
//		assertEquals(1, group.coops.count());
//	}
//
//	@Test
//	public void coopWithGroup() {
//		Query<Coop> coops = Coop.all().filter("title", "first");
//		assertEquals(1, coops.count());
//		Coop coop = coops.get();
//		assertEquals("first (part of group)", coop.toString());
//		CoopGroup group = CoopGroup.all().getByKey(coop.group.id);
//		assertEquals("group", group.title);
//	}
//
//	@Test
//	public void coopWithoutGroup() {
//		Query<Coop> coops = Coop.all().filter("title", "second");
//		assertEquals(1, coops.count());
//		assertEquals("second", coops.get().toString());
//	}
}
