package models;

import java.util.Collection;
import java.util.TreeSet;

import play.data.validation.Required;
import siena.*;

@Table("member")
public class Member extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("name")
	@Required
	@Max(50)
	@NotNull
	public String name;

	@Column("coop")
	@Required
	@NotNull
	public Coop coop;

	@Filter("member")
	Query<User> users;

	public Member(String name, Coop coop) {
		this.name = name;
		this.coop = coop;
	}

	@Override
	public String toString() {
		return name;
	}

	public static Query<Member> all() {
		return Model.all(Member.class);
	}

	public static Collection<Member> findByUser(String account) {
		return User.findByAccount(account);
	}
}
