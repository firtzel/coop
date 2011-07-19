package utils;

import play.test.UnitTest;
import org.junit.Test;

public class QuantityTest extends UnitTest {

	@Test
	public void byWeight() {
		assertEquals("1.5 kg", new Quantity(QuantityType.WEIGHT, 1.5f).toString());
	}

	@Test
	public void byUnits() {
		assertEquals("5.0 units", new Quantity(QuantityType.UNITS, 5f).toString());
	}

	@Test
	public void fromString() {
		assertEquals("13.0 units", Quantity.fromString("13.0 units").toString());
		assertEquals("3.5 kg", Quantity.fromString("3.5 kg").toString());
	}
}
