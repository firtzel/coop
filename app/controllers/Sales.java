package controllers;

import play.modules.gae.GAE;
import play.mvc.Before;

public class Sales extends Application {

    @Before
    static void checkConnected() {
        if(GAE.getUser() == null) {
            Application.login();
        } else {
            renderArgs.put("user", GAE.getUser().getEmail());
        }
    }

    public static void index() {
    }

}
