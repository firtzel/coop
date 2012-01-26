package utils;

import com.google.appengine.api.users.User;

public interface LoginManager {

	public boolean isLoggedIn();

	public void login(String returnAction);

	public void logout(String returnAction);

	public User getUser();
}
