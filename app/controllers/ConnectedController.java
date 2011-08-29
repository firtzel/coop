package controllers;

import models.Member;
import models.User;
import play.modules.gae.GAE;
import play.mvc.Before;
import play.mvc.Controller;

public abstract class ConnectedController extends Application {

	@Before
	static void checkConnected() {
		if (GAE.getUser() == null) {
			Application.login();
		} else {
			String account = GAE.getUser().getEmail();
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
