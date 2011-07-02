package models;

import play.db.jpa.Model;

public class Supplier extends Model {

	public String name;
	public Coop coop;
	
}
