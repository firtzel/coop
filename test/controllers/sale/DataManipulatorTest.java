package controllers.sale;

import java.util.List;

import models.ProductOrder;
import models.Sale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.test.UnitTest;
import utils.TestUtils;

public class DataManipulatorTest extends UnitTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("test-data.yml");
	}

	@After
	public void tearDown() {
		SienaFixtures.deleteDatabase();
	}

	@Test
	public void testBuildMemberOrders() {
		Sale sale = TestUtils.getFirstSale();
		List<ProductOrder> orders = sale.productOrders.fetch();
		assertEquals(4, orders.size());
		long memberId = orders.get(0).member.id;
		assertEquals(28.5, DataManipulator.calcMemberTotalOrder(memberId, orders), 0.01);
	}
}
