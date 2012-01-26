package utils;

import play.Logger;
import play.mvc.Controller;
import play.mvc.results.Ok;

import com.google.appengine.api.users.User;

import controllers.Application;

public class MockLoginManager extends Controller implements LoginManager {
	private static boolean isLoggedIn;
	private static final User USER = new User("test@gmail.com", "mock.com");

	public MockLoginManager() {
		setLoggedIn(false);
	}

	@Override
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	private static void display(String message) {
		redirect("Application.index", "");
	}

	@Override
	public void login(String returnAction) {
		setLoggedIn(true);
		display("you are now logged-in");
	}

	private void setLoggedIn(boolean newIsLoggedIn) {
		isLoggedIn = newIsLoggedIn;
	}

	@Override
	public void logout(String returnAction) {
		setLoggedIn(false);
		display("you can go home now");
	}

	@Override
	public User getUser() {
		return (isLoggedIn() ? USER : null);
	}
}
