package exam2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam1.Greeter;

@Controller
public class GreeterController {
	private Greeter greeter;
	
	public GreeterController() {
		
	}
	
	public GreeterController(Greeter greeter) {
		this.greeter = greeter;
	}
	
	// request method
	@GetMapping("/exam2/hello") // http://localhost:8080/myProject/exam2/hello?guest=손흥민
	public String hello(Model model, @RequestParam(value="guest", required=false /*값이 없을 시 오류가 발생하지 않고, null값이 들어감*/) String guest) { //model = request Storage -> model에 저장 = setAttribute
		
		String msg = greeter.greet(guest);
		model.addAttribute("msg", msg);
		
		return "hello"; // "" = view의 이름
	}
}
