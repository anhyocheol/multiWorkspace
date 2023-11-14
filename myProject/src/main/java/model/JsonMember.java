package model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMember {

	private MemberDAO memberDAO;
	
	public JsonMember() {		
	}
	
	public JsonMember(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@SuppressWarnings("unchecked")
	public String getAllMemberJson() {
		ArrayList<MemberDO> memberList = this.memberDAO.getAllMembers();
		JSONArray memberArray = new JSONArray();
		JSONObject memberJson = null;
		
		for(MemberDO member: memberList) {
			memberJson = new JSONObject();
			
			memberJson.put("id", member.getId());
			memberJson.put("name", member.getName());
			memberJson.put("regdate", member.getRegdate());
			
			memberArray.add(memberJson);
		}
		
		return memberArray.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public String getMemberJson(String id) {
		MemberDO member = memberDAO.getMember(id);		
		JSONObject memberJson = null;
		String jsonResult = "";
		
		if(member != null) {
			memberJson = new JSONObject();
			
			memberJson.put("id", member.getId());
			memberJson.put("passwd", member.getPasswd());
			memberJson.put("name", member.getName());
			memberJson.put("regdate", member.getRegdate());
			memberJson.put("grade", member.getGrade());
			
			jsonResult = memberJson.toJSONString();
		}
		
		return jsonResult;
	}
	
	@SuppressWarnings("unchecked")
	public String getResult(int rowCount) {
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("result", rowCount);
		
		return jsonObject.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public String getMessage(String msg) {
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("msg", msg);
		
		return jsonObject.toJSONString();
	}
}





















