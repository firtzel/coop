package models;

import play.test.*;
import org.junit.*;

public class CoopTest extends UnitTest {

	@Test
	public void aCoop() {
		assertEquals(new Coop("1", "2", new CoopGroup("3", "4")), "something");
	}
}
