package controllers;

import play.*;
import play.modules.gae.GAE;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        if(GAE.isLoggedIn()) {
            Members.index();
        }
        render();
//    	String name = "Ophir";
//    	render(name);
    }

    public static void login() {
        GAE.login("Application.index");
    }
    
    public static void logout() {
        GAE.logout("Application.index");
    }
}