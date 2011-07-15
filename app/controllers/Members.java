package controllers;

import java.util.Collection;
import java.util.List;

import models.Member;

import play.modules.gae.GAE;
import play.mvc.Before;

public class Members extends Application {

	@Before
	static void checkConnected() {
		if (GAE.getUser() == null) {
			Application.login();
		} else {
			renderArgs.put("user", GAE.getUser().getEmail());
		}
	}

	public static void index() {
		Collection<Member> members = Member.findByUser(getUser());
		render(members);
	}

	// ~~~~~~ utils

	static String getUser() {
		return renderArgs.get("user", String.class);
	}
}
