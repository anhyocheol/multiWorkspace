package exam1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Exam1Main {
 
	public static void main(String[] args) {
	
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppContext.class); // Spring Contanier 만듬
		
		Greeter g1 = context.getBean("greeter", Greeter.class); // Bean의 이름과 타입(중복될 경우 에러발생)을 이용하여 contanier에 저장된 것을 사용할 수 있음
		Greeter g2 = context.getBean("greeter", Greeter.class);
		
		if(g1 == g2) {
			System.out.println("싱글턴 객체입니다!");
		} 
		else {
		System.out.println("두 객체는 서로 다른 객체입니다!");
		}
		
		String msg = g1.greet("손흥민");
		System.out.println(msg);
		
		context.close();
	}
}
