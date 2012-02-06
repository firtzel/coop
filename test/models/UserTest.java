package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.test.UnitTest;
import utils.TestUtils;

public class UserTest extends UnitTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
	    SienaFixtures.loadModels("coops.yml", "users.yml");
	}

	@After
	public void tearDown() {
		SienaFixtures.deleteDatabase();
	}

	@Test
	public void getMember1() {
		Member member = TestUtils.getMember("Dayan");
		assertNotNull(member);
		assertEquals(1, member.users.count());
		assertEquals("[ben@gmail.com (Dayan)]", member.users.fetch().toString());
	}

	@Test
	public void getMember2() {
		Member member = TestUtils.getMember("Rosen");
		assertNotNull(member);
		assertEquals(2, member.users.count());
		assertEquals("[josh@gmail.com (Rosen), dude@gmail.com (Rosen)]", member.users.fetch().toString());
	}

	@Test
	public void count() {
		assertEquals(2, Member.all().count());
		assertEquals(3, User.all().count());
	}

	@Test
	public void fetchUsers() {
		assertEquals("[josh@gmail.com (Rosen), dude@gmail.com (Rosen), ben@gmail.com (Dayan)]", User.all().fetch().toString());  
	}

	@Test
	public void findByAccount() {
		assertEquals("[Rosen]", User.findByAccount("josh@gmail.com").toString());
		assertEquals("[Dayan]", User.findByAccount("ben@gmail.com").toString());
		assertEquals("[]", User.findByAccount("nobody@gmail.com").toString());
	}
}
