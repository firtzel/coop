package utils;

import com.google.appengine.api.users.User;

import play.modules.gae.GAE;

public class GaeLoginManager implements LoginManager {

	@Override
	public boolean isLoggedIn() {
		return GAE.isLoggedIn();
	}

	@Override
	public void login(String returnAction) {
		GAE.login(returnAction);
	}

	@Override
	public void logout(String returnAction) {
		GAE.logout(returnAction);
	}

	@Override
	public User getUser() {
		return GAE.getUser();
	}
}
