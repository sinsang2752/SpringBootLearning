package com.shinhan.firstzone.controller2;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.firstzone.security.MemberService;
import com.shinhan.firstzone.service2.WebBoardService;
import com.shinhan.firstzone.vo2.MemberEntity;
import com.shinhan.firstzone.vo4.WebBoardDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/webboard")
@RequiredArgsConstructor
@Log4j2
public class WebBoardController {

	final WebBoardService boardService;
	
	final MemberService memService;
	
	@GetMapping("/register")
	void registerForm() {
		
	}
	
	@GetMapping("/detail")
	void detail(Long bno, Model model) {
		model.addAttribute("board", boardService.selectById(bno));
	}
	
	@PostMapping("/modify")
	String modify(WebBoardDTO dto) {
		boardService.modify(dto);
		return "redirect:list";
	}
	
	@PostMapping("/register")
	String register(WebBoardDTO dto, Principal principal, Authentication authentication, HttpSession session) {
		System.out.println("방법1 principal: " + principal);
		
		Object principal2 = authentication.getPrincipal();
		System.out.println("방법2 authentication: " + principal2);
		
		Object principal3 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("방법3 authentication: " + principal3);
		
		UserDetails userDetails = (UserDetails) principal3;
		System.out.println("UserDetails: " + userDetails);
		
		String mid = principal.getName();
		UserDetails userDetails2 = memService.loadUserByUsername(mid);
		System.out.println("방법4 loadUserByUsername: " + userDetails2);
		
		dto.setMid(mid);
		
		log.info("입력 before: " + dto);
		Long bno = boardService.register(dto);
		log.info(bno + "번 게시글이 등록됨");
		return "redirect:list";
	}
	
	
	@GetMapping("/list")
	String getList(Model model) {
		List<WebBoardDTO> blist = boardService.getList();
		model.addAttribute("blist",blist);
		return "webboard/boardlist";
	}
	
	@PostMapping("/delete")
	String delete(Long bno) {
		boardService.delete(bno);
		return "redirect:list";
	}
}
