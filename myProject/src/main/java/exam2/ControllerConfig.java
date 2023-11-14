package exam2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exam1.Greeter;

@Configuration // 설정 class가 되려면 반드시 붙여줘야함
public class ControllerConfig {

	@Bean
	public Greeter greeter() {		
		Greeter g = new Greeter();
		g.setFormat("%s님의 방문을 환영합니다.");
		
		return g;
	}
	
	@Bean
	public GreeterController greeterController() {
		return new GreeterController(this.greeter());
	}
	
}
