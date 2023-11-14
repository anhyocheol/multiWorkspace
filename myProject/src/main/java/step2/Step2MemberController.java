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
	1) GET  | /step2/list			=> 전체 사용자 정보와 함께 memberList.jsp
	2) POST | /step2/insert			=> 인서트 작업 후 memberList.jsp
	3) GET  | /step2/modify			=> 선택한 사용자 정보와 함께 memberModify.jsp
	4) POST	| /step2/changePasswd	=> 비밀번호 변경 후 memberList.jsp
	5) POST	| /step2/changeGrade	=> 등급 변경 후 memberList.jsp
	6) POST	| /step2/deleteMember	=> 회원 삭제 후 memberList.jsp
*/

@Controller
public class Step2MemberController {

	private MemberDAO memberDAO;
	
	public Step2MemberController() {
	}
	
	public Step2MemberController(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@GetMapping("/step2/test")
	public String test() {
		
		return "test";
	}
	
	@GetMapping("/step2/list")		// 	
	public String list(Model model) {
		model.addAttribute("memberList", memberDAO.getAllMembers());
		
		return "memberList";
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
























