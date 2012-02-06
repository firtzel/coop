package dto;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.MemberSaleDetails;

import com.google.gson.Gson;

public class SaleDetailsDto {
	private List<MemberSaleDetailsDto> memberDetails;
	
	public SaleDetailsDto() {
		this.memberDetails = new ArrayList<MemberSaleDetailsDto>();
	}

	public void addMemberDetails(MemberSaleDetailsDto details) {
		memberDetails.add(details);
	}

	public static class MemberSaleDetailsDto {
		public final long id;
		public final String name;
		public final String phoneNumber;
		public final boolean ordered;
		public final boolean orderTaken;
		public final float orderPrice;
		public final float payment;
		public final float debt;
		public final String comment;

		public MemberSaleDetailsDto(Member member, boolean ordered,
				float orderPrice, MemberSaleDetails details) {
			this.id = member.id;
			this.name = member.name;
			this.phoneNumber = member.phoneNumber;
//			this.ordered = details.ordered;
			this.ordered = ordered;
			this.orderTaken = details.orderTaken;
//			this.orderPrice = details.orderPrice;
			this.orderPrice = orderPrice;
			this.payment = details.payment;
			this.debt = details.debt;
			this.comment = details.comment;
		}
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public List<MemberSaleDetailsDto> getMemberDetails() {
		return memberDetails;
	}
}
