package com.shinhan.firstzone.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shinhan.firstzone.repository.MemberRespository;
import com.shinhan.firstzone.vo2.MemberEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private MemberRespository mrepo;
	@Autowired
	PasswordEncoder passwordEncoder;  
	// 회원가입
	//@Transactional
	public MemberEntity joinUser(MemberEntity member) {
		// 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		String pwd = passwordEncoder.encode(member.getMpassword());
		member.setMpassword(pwd);
		return mrepo.save(member);
	}
	//!!!!반드시 구현해야한다.  (로그인)
	@Override 
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername mid:" + userName);
	    Optional<MemberEntity> optionMember =  mrepo.findById(userName);
	    MemberEntity loginUser = optionMember.orElse(null);
	    // spring의 Security User
		UserDetails member = optionMember.filter(m -> m != null)
				.map(m -> new SecurityUser(m)).get();
		httpSession.setAttribute("user", loginUser);
		return member;
	}
}

