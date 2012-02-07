package utils;

import java.net.HttpURLConnection;

import org.junit.After;
import org.junit.Before;

import play.modules.siena.SienaFixtures;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public abstract class CoopFunctionalTest extends FunctionalTest { 

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

	public void login() {
		Response response = POST("/application/login");
		assertStatus(HttpURLConnection.HTTP_MOVED_TEMP, response);
	}

	public void logout() {
		Response response = POST("/application/logout");
		assertStatus(HttpURLConnection.HTTP_MOVED_TEMP, response);
	}
}
