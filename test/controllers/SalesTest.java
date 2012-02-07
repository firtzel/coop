package controllers;

import java.net.HttpURLConnection;
import java.text.ParseException;

import models.Sale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.mvc.Http.Response;
import utils.CoopFunctionalTest;
import utils.TestUtils;

public class SalesTest extends CoopFunctionalTest {

	@Test
	public void myOrders() throws ParseException {
		Sale first = TestUtils.getFirstSale();
		Response response = GET(String.format("/sales/%d/mine", first.id));
		assertStatus(HttpURLConnection.HTTP_MOVED_TEMP, response);
		login();
		response = GET(String.format("/sales/%d/mine", first.id));
		assertStatus(HttpURLConnection.HTTP_OK, response);
	}

	@Test
	public void testManageJson() {
		Sale first = TestUtils.getFirstSale();
		login();
		Response response = GET(String.format("/sales/%d/manage.json", first.id));
		assertStatus(HttpURLConnection.HTTP_OK, response);
	}
}
