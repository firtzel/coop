package models;

import java.util.Arrays;
import java.util.Collection;

import play.test.*;
import org.junit.*;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;

//@RunWith(value = Parameterized.class)
public class CoopTest extends UnitTest {

//	private String coopTitle;
//	private String coopGroupTitle;
//	private String expResult;

//	public CoopTest(String coopTitle, String coopGroupTitle, String expResult) {
//		this.coopTitle = coopTitle;
//		this.coopGroupTitle = coopGroupTitle;
//		this.expResult = expResult;
//	}

//	@Parameters
//	public static Collection<Object[]> data() {
//		Object[][] data = new Object[][] { { "coop", "coop-group", "coop (part of coop-group)" } };
//		return Arrays.asList(data);
//	}

	private static void createCoop(String expResult, String coopTitle, CoopGroup coopGroup) {
		assertEquals(expResult, new Coop(coopTitle, "", coopGroup).toString());
	}

	@Test
	public void createCoopWithGroup() {
		createCoop("friendly coop (part of group)", "friendly coop", new CoopGroup("group", ""));
	}

	@Test
	public void createCoopWithoutGroup() {
		createCoop("loner coop", "loner coop", null);
	}
}
