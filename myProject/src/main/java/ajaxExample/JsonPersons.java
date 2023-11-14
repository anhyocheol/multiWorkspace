package ajaxExample;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonPersons {

	@SuppressWarnings("unchecked")
	public String getPersons() {
		Person person1 = new Person("손흥민", "남자", 31, "축구");
		Person person2 = new Person("김하성", "남자", 27, "야구");
		Person person3 = new Person("김연경", "여자", 37, "배구");
		
		ArrayList<Person> personsList = new ArrayList<Person>(); 
		personsList.add(person1);
		personsList.add(person2);
		personsList.add(person3);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		for(Person person : personsList) {
			jsonObject = new JSONObject();

			jsonObject.put("name", person.getName());
			jsonObject.put("gender", person.getGender());
			jsonObject.put("age", person.getAge());
			jsonObject.put("field", person.getField());
		
			jsonArray.add(jsonObject);
		}
		
		
		return jsonArray.toJSONString();
	}
}
