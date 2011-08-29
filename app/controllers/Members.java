package controllers;

import java.util.Collection;
import java.util.List;

import models.Member;

import play.modules.gae.GAE;
import play.mvc.Before;

public class Members extends ConnectedController {

	public static void index() {
		Collection<Member> members = Member.findByUser(getUser());
		render(members);
	}

	public static void details(Long id) {
		Member member = Member.all().getByKey(id);
		render(member);
	}
}
