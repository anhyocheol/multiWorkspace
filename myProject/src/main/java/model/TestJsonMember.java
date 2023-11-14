package model;

public class TestJsonMember {

	public static void main(String[] args) {
		MemberDAO memberDAO = new MemberDAO();
		JsonMember jsonMember = new JsonMember(memberDAO);
		
		System.out.println(jsonMember.getAllMemberJson());
		System.out.println("-----------------------------------------------------------------");
		System.out.println(jsonMember.getMemberJson("longlee@daum.net"));
	}
}
