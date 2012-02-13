package models;

import java.util.Collection;
import java.util.TreeSet;

import play.data.validation.Required;
import siena.*;

@Table("member")
public class Member extends Model implements Comparable<Member> {

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

	@Column("phone_number")
	@Required
	@NotNull
	public String phoneNumber;

	@Filter("member")
	public Query<User> users;

	@Filter("member")
	public Query<MemberSaleDetails> saleDetails;
	
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

	public static Member getById(Long id) {
		return all().getByKey(id);
	}

	public static Collection<Member> findByUser(String account) {
		return User.findByAccount(account);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Member)) {
			return false;
		}
		Member other = (Member) obj;
		return (compareTo(other) == 0);
	}

	@Override
	public int compareTo(Member other) {
		return this.id.compareTo(other.id);
	}
}
