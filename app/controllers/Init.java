package controllers;

import java.util.List;

import models.Coop;
import play.mvc.Controller;
import utils.Bootstrap;

public class Init extends Controller {

    public static void load() {
    	Bootstrap.loadInitialData();
    	render();
    }
}
