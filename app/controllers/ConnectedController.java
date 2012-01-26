package controllers;

import javax.inject.Inject;

import models.Member;
import models.User;
import play.Logger;
import play.modules.gae.GAE;
import play.mvc.Before;
import play.mvc.Controller;
import utils.LoginManager;

public abstract class ConnectedController extends Application {

	@Inject
	private static LoginManager loginManager;

	@Before
	static void checkConnected() {
		if (loginManager.getUser() == null) {
			Logger.info("user not logged-in");
			Application.login();
		} else {
			String account = loginManager.getUser().getEmail();
			User user = User.getByAccount(account);
			if (user == null) {
				Application.loginError(account);
			} else {
				renderArgs.put("user", user);
				renderArgs.put("member", user.member);
			}
		}
	}

	// ~~~~~~ utils

	static String getUser() {
		User user = renderArgs.get("user", User.class);
		return user.account;
	}

	static Member getMember() {
		return renderArgs.get("member", Member.class);
	}
}
