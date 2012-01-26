package utils;

import models.Member;
import models.Sale;

public class TestUtils {
	public static Sale getFirstSale() {
		return Sale.all().fetch().get(1);
	}

	public static Sale getEmptySale() {
		return Sale.all().fetch().get(0);
	}

	public static Member getMember(String name) {
		return Member.all().filter("name", name).get();
	}
}
