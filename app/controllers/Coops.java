package controllers;

import java.util.List;

import models.Coop;
import play.mvc.Controller;

public class Coops extends Controller {

    public static void list() {
    	List<Coop> coops = Coop.all().fetch();
    	render(coops);
    }
}
