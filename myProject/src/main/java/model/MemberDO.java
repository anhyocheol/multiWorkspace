package model;

public class MemberDO {

	private String id;
	private String passwd;
	private String newPasswd;
	private String name;
	private String regdate;
	private int grade;
	
	public MemberDO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNewPasswd() {
		return this.newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
