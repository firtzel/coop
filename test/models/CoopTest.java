package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.test.UnitTest;
import siena.Query;

public class CoopTest extends UnitTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("coops.yml", "sales.yml", "users.yml");
	}

	@After
	public void tearDown() {
		SienaFixtures.deleteDatabase();
	}

	@Test
	public void count() {
		assertEquals(2, CoopGroup.all().count());
		assertEquals(2, Coop.all().count());
	}

	@Test
	public void getEmptyGroup() {
		CoopGroup empty = CoopGroup.all().filter("title", "empty").get();
		assertEquals("empty", empty.title);
		assertEquals(0, empty.coops.count());
	}

	@Test
	public void getGroup() {
		CoopGroup group = CoopGroup.all().filter("title", "group").get();
		assertEquals("group", group.title);
		assertEquals(1, group.coops.count());
	}

	@Test
	public void coopWithGroup() {
		Query<Coop> coops = Coop.all().filter("title", "first");
		assertEquals(1, coops.count());
		Coop coop = coops.get();
		assertEquals("first (part of group)", coop.toString());
		CoopGroup group = CoopGroup.all().getByKey(coop.group.id);
		assertEquals("group", group.title);
		assertEquals(2, coop.sales.count());
		assertEquals(2, coop.members.count());
	}

	@Test
	public void coopWithoutGroup() {
		Query<Coop> coops = Coop.all().filter("title", "second");
		assertEquals(1, coops.count());
		Coop coop = coops.get();
		assertEquals("second", coop.toString());
		assertEquals(0, coop.sales.count());
		assertEquals(0, coop.members.count());
	}
}
