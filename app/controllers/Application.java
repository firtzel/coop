package controllers;

import play.*;
import play.modules.gae.GAE;
import play.mvc.*;
import utils.LoginManager;

import java.util.*;

import javax.inject.Inject;

import models.*;

public class Application extends Controller {
	
	@Inject
	private static LoginManager loginManager;

    public static void index() {
        if(loginManager.isLoggedIn()) {
            Members.index();
        }
        render();
    }

    public static void login() {
    	loginManager.login("Application.index");
    }
    
    public static void logout() {
    	loginManager.logout("Application.index");
    }

	public static void loginError(String account) {
		render(account);
	}
}