package controllers;

import java.net.HttpURLConnection;

import models.Coop;

import org.junit.Test;

import play.mvc.Http.Response;
import utils.CoopFunctionalTest;
import utils.TestUtils;

public class CoopsTest extends CoopFunctionalTest {

	@Test
	public void testManageJson() {
		Coop first = TestUtils.getFirstCoop();
		login();
		Response response = GET(String.format("/coops/%d/sale/new.json", first.id));
		assertStatus(HttpURLConnection.HTTP_OK, response);
	}
}
