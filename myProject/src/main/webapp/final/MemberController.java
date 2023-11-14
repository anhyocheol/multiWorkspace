package controller;

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
2) 검색			|GET 	| /toMarket			-> 검색어 파라미터 값을 가지고 marketController.java 	-> marketList.jsp(검색어)
3) 로그인			|GET 	| /toLogin			-> login.jsp
4) 회원가입 		|GET	| /toJoin			-> join.jsp
5) 시장리스트		|GET 	| /toMarket			=> 카테고리 파라미터 값을 가지고 marketController.java 	-> marketList.jsp(카테고리)
6) 시끌시끌		|GET 	| /toPost 			-> post.html 

*로그인
7)로그인			|POST	| /loginMember		-> 로그인 처리 후 main.jsp

*회원가입
8)회원가입			|POST	| /joinMember		-> 회원가입 처리 후 login.jsp

*마이페이지
9)비밀번호변경		|POST	| /updatePasswd		-> 사용자 입력값을 통해 수정 후 login.jsp
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
	@GetMapping("/Main")
	public String Main() {
		
		return "main";
	}
	@GetMapping("/Login")
	public String toLogin() {
		
		return "login";
	}
	@GetMapping("/Join")
	public String toJoin() {
		
		return "join";
	}
	@GetMapping("/Post")
	public String Post() {
		
		return "redirect:/post.html";
	}
	@GetMapping("/Market")
	public String Market(@RequestParam String listKeyword) {
		return "redirect:/market" + listKeyword;
	}
	
	//로그인 화면
	@PostMapping("/loginMember")
	public String loginMember(@ModelAttribute MemberDO command, Model model) {
		String viewName = "";
		
		try {
			memberDAO.checkMember(command);
			viewName = "redirect:/main";
			
		}catch(Exception e) {
			model.addAttribute("msg", e.getMessage());
			
			viewName = "login";
		}
		return viewName;
	}
	
	//회원가입 화면
	@PostMapping("/joinMember")
	public String joinMember(@ModelAttribute MemberDO command, Model model) {
		String viewName = "";
		
		try {
			// int rowCount = memberDAO.insertMember(command);는 사용안할 거라 뺌
			memberDAO.insertMember(command);
			viewName = "redirect:/login";
			
		}catch(Exception e) {
			model.addAttribute("msg", e.getMessage());
			
			viewName = "join";
		}
		return viewName;
	}
	
	// 회원 계정 화면
	@GetMapping("/myPage")
	public String myPage(@RequestParam("id") String id, Model model) {
		model.addAttribute("member", memberDAO.getMemberHistory(id));
		
		return "myPage";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		model.addAttribute("member", memberDAO.getMemberInfo(id));
		
		return "updateMember";
	}
	
	@PostMapping("/updatePasswd")
	public String updatePasswd(@ModelAttribute MemberDO command) {
		memberDAO.updatePasswd(command);
			
		return "redirect:/myPage";
	}
	
	@PostMapping("/updateNickname")
	public String updateNickname(@ModelAttribute MemberDO command) {
		memberDAO.updateNickname(command);
			
		return "redirect:/myPage";
	}
	
	@PostMapping("/deleteMember")
	public String deleteMember(@RequestParam("id") String id) {
		memberDAO.deleteMember(id);
		
		return "redirect:/main";
	}
}
