package controllers;

import play.modules.gae.GAE;
import play.mvc.Before;
import play.mvc.Controller;

public abstract class ConnectedController extends Application {

	@Before
	static void checkConnected() {
		if (GAE.getUser() == null) {
			Application.login();
		} else {
			renderArgs.put("user", GAE.getUser().getEmail());
		}
	}

	// ~~~~~~ utils

	static String getUser() {
		return renderArgs.get("user", String.class);
	}
}
