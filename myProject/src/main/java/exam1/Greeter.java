package exam1;

public class Greeter {

	private String format; // System.out.printf("%님의 방문을 환영합니다.","손흥민");
	
	public Greeter() {
	}
	
	public  void setFormat(String format) {
		this.format = format;
	}
	
	public String greet(String guest) {
		return String.format(this.format, guest);
	}
	
}
