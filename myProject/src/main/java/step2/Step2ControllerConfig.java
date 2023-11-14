package step2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.MemberDAO;

@Configuration
public class Step2ControllerConfig {

	@Bean
	public MemberDAO mebmerDAO() {		
		return new MemberDAO();
	}
	
	@Bean
	public Step2MemberController step2MemberController() {
		return new Step2MemberController(this.mebmerDAO());
	}
}
