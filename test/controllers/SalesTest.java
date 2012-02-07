package controllers;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.BaseProduct;
import models.Product;
import models.Sale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.modules.siena.SienaFixtures;
import play.mvc.Http;
import play.mvc.Http.Response;
import play.test.FunctionalTest;
import play.test.UnitTest;
import utils.DateUtils;
import utils.TestUtils;

public class SalesTest extends FunctionalTest {

	@Before
	public void setUp() {
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("test-data.yml");
	}

	@After
	public void tearDown() {
		SienaFixtures.deleteDatabase();
		logout();
	}

//	@Test
	public void login() {
		Response response = POST("/application/login");
		assertStatus(HttpURLConnection.HTTP_MOVED_TEMP, response);
	}

	@Test
	public void logout() {
		Response response = POST("/application/logout");
		assertStatus(HttpURLConnection.HTTP_MOVED_TEMP, response);
	}

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
		assertContentEquals("{\"memberDetails\":[{\"id\":697,\"name\":\"me\",\"phoneNumber\":\"054-654321\",\"ordered\":true,\"orderTaken\":true,\"orderPrice\":28.5,\"payment\":10.0,\"debt\":0.0,\"comment\":\"\"}," +
				"{\"id\":698,\"name\":\"Rosen\",\"phoneNumber\":\"08-931909\",\"ordered\":false,\"orderTaken\":false,\"orderPrice\":0.0,\"payment\":0.0,\"debt\":0.0,\"comment\":\"\"}," +
				"{\"id\":699,\"name\":\"Dayan\",\"phoneNumber\":\"077-123456\",\"ordered\":true,\"orderTaken\":false,\"orderPrice\":26.5,\"payment\":0.0,\"debt\":0.0,\"comment\":\"\"}]}", response);
	}
}
