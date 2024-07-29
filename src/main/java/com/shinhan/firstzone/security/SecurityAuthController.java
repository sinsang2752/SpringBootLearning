package com.shinhan.firstzone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.firstzone.vo2.MemberEntity;

@Controller
@RequestMapping("/auth")
public class SecurityAuthController {

	@Autowired
	MemberService mService;
	
	@GetMapping("/login")
	public void f1() {}
	
	@GetMapping("/loginSuccess")
	public void f2() {}
	
	@GetMapping("/logout")
	public void f3() {}
	
	@GetMapping("/accessDenined")
	public void f4() {}
	
	@GetMapping("/signup")
	public String f5() {
		return "/auth/joinForm";
	}
	
	@PostMapping("/joinProc")
	@ResponseBody
	public String f6(MemberEntity member) {
		MemberEntity newMember = mService.joinUser(member);
		return"redirect:login";
	}
}
