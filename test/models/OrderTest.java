package models;

import java.text.ParseException;
import java.util.Date;

import play.test.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.test.UnitTest;
import siena.Model;
import siena.Query;
import utils.DateUtils;

public class OrderTest extends UnitTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("coops.yml", "sales.yml", "users.yml",
				"orders.yml");
	}

	@After
	public void tearDown() {
		SienaFixtures.deleteDatabase();
	}

	@Test
	public void count() {
		assertEquals(3, Model.all(Order.class).count());
		assertEquals(3, Model.all(ProductOrder.class).count());
	}

	@Test
	public void getFirstOrder() throws ParseException {
		Order order = Order.getByDate(DateUtils.DATE_FORMAT.parse("2011-02-05")).get();
		assertNotNull("Could not retrieve order from 2011-02-05", order);
		assertEquals("Ordered by Rosen at 2011-02-05 as part of Date: 2011-02-08 Coop: first (part of group)", order.toString());
		assertEquals("[1.5 kg of lentils, 3.0 units of bread]", order.products.fetch().toString());
	}

	@Test
	public void getSecondOrder() throws ParseException {
		Order order = Order.getByDate(DateUtils.DATE_FORMAT.parse("2011-02-06")).get();
		assertNotNull("Could not retrieve order from 2011-02-06", order);
		assertEquals("Ordered by Dayan at 2011-02-06 as part of Date: 2011-02-08 Coop: first (part of group)", order.toString());
		assertEquals("[2.0 kg of lentils]", order.products.fetch().toString());
	}
}
