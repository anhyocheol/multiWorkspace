package step2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.MemberDAO;
import model.MemberDO;

/*
*메인
최초시작 => memberController.jsp 	-> main.jsp

1) 메인			|GET 	| /toMain 			-> main.jsp
2) 검색			|GET 	| /toMarket			-> 사용자 검색 값과 함께 search.jsp
3) 로그인			|GET 	| /toLogin			-> login.jsp
4) 회원가입 		|GET	| /toJoin			-> join.jsp
5) 시장리스트		|GET 	| /toMarket			=> marketController.jsp 	-> marketList.jsp
6) 점포등록		|GET 	| /toMarket			=> marketController.jsp 	-> storeRegister.jsp
7) 시끌시끌		|GET 	| /toPost 			=> postController.jsp 		-> post.html

*로그인
8)로그인			|POST	| /loginMember		-> 로그인 처리 후 main.jsp

*회원가입
9)회원가입			|POST	| /joinMember		-> 회원가입 처리 후 login.jsp

*마이페이지
10)비밀번호변경		|POST	| /updatePasswd		-> 사용자 입력값을 통해 수정 후 login.jsp
10)닉네임변경		|POST	| /updateNickname	-> 사용자 입력값을 통해 수정 후 login.jsp
11)회원정보안내		|POST	| /getAllMemberInfo	-> 회원정보를 읽은 후 myPage.jsp
12)회원탈되		|POST	| /deleteMember			-> 회원탈퇴 처리 후, main.jsp


*/

@Controller
public class MemberController {

	private MemberDAO memberDAO;
	
	public MemberController() {
	}
	
	public MemberController(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	// 메인화면
	@GetMapping("/toMain")
	public String toMain() {
		
		return "main";
	}
	
	// 검색
		@GetMapping("/toMarket?search=")
		public String main() {
			String viewName = "";
			
			viewName = "redirect:/toMarket?search=" + memberDAO.getAllMembers();
		
			return viewName;
		}
	
	@GetMapping("/list")		// http://localhost:8080/myProject/step2/list
	public String list(Model model) {
		model.addAttribute("memberList", memberDAO.getAllMembers());
		
		return "main";
	}
	
	@PostMapping("/step2/insert")
	public String insert(@ModelAttribute MemberDO command, Model model) {
		String viewName = "";
		
		try {
			memberDAO.insertMember(command);
			viewName = "redirect:/step2/list";
		}
		catch(Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("memberList", memberDAO.getAllMembers());
			
			viewName = "memberList";
		}
		
		return viewName;
	}
	
	@GetMapping("/step2/modify")
	public String modify(@RequestParam("id") String id, Model model) {
		model.addAttribute("member", memberDAO.getMember(id));
		
		return "memberModify";
	}
	
	@PostMapping("/step2/changePasswd")
	public String changePasswd(@ModelAttribute MemberDO command) {
		memberDAO.changePasswd(command);
		
		return "redirect:/step2/list";
	}
	
	@PostMapping("/step2/changeGrade")
	public String changeGrade(@ModelAttribute MemberDO command) {
		memberDAO.changeGrade(command);
		
		return "redirect:/step2/list";
	}
	
	@PostMapping("/step2/deleteMember")
	public String deleteMember(@RequestParam("id") String id) {
		memberDAO.deleteMember(id);
		
		return "redirect:/step2/list";
	}
}
























