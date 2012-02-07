package utils;

import models.Coop;
import models.Member;
import models.Sale;

public class TestUtils {
	public static Sale getFirstSale() {
		return Sale.getByName("first");
	}

	public static Sale getEmptySale() {
		return Sale.getByName("empty");
	}

	public static Member getMember(String name) {
		return Member.all().filter("name", name).get();
	}

	public static Coop getFirstCoop() {
		return Coop.getByTitle("first");
	}
}
