package ajaxExample;

public class Person {

	private String name;
	private String gender;
	private int age;
	private String field;
	
	public Person() {
	}
	
	public Person(String name, String gender, int age, String field) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	
}
