package controllers;

import models.Coop;
import models.Sale;
import play.modules.gae.GAE;
import play.mvc.Before;

public class Sales extends ConnectedController {

    public static void details(Long id) {
    	Sale sale = Sale.all().getByKey(id);
    	render(sale);
    }

}
