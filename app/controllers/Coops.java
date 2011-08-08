package controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import models.Coop;
import models.Member;
import models.Sale;

public class Coops extends ConnectedController {

	public static void list() {
//		List<Coop> coops = Coop.all().fetch();
//		render(coops);
		Collection<Member> members = Member.findByUser(getUser());
		Collection<Coop> coops = Coop.findByMember(members);
		render(coops);
	}

	public static void details(Long id) {
		Coop coop = Coop.all().getByKey(id);
		render(coop);
	}
}
