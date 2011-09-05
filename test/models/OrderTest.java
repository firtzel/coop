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
		assertEquals(3, Model.all(ProductOrder.class).count());
	}

	@Test
	public void getFirstOrder() throws ParseException {
		Sale sale = Sale.getByDate(DateUtils.DATE_FORMAT.parse("2011-02-08")).get();
		assertNotNull("Could not retrieve sale from 2011-02-08", sale);
		assertEquals("[1.5 kg of lentils, 3.0 units of bread, 2.0 kg of lentils]", sale.productOrders.fetch().toString());
	}

//	@Test
//	public void getSecondOrder() throws ParseException {
//		Sale sale = Sale.getByDate(DateUtils.DATE_FORMAT.parse("2011-02-06")).get();
//		assertNotNull("Could not retrieve sale from 2011-02-06", sale);
//		assertEquals("[2.0 kg of lentils]", sale.productOrders.fetch().toString());
//	}
}
