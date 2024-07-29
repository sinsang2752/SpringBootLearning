package com.shinhan.firstzone;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shinhan.firstzone.repository.MemberRespository;

@SpringBootTest
public class SecurityTest {
	
	@Autowired
	MemberRespository mRepo;

	@Autowired
	PasswordEncoder passEncoder;
	
	@Test
	void makePass() {
		List<String> ids = new ArrayList<>();
		
		ids.add("user1");
		ids.add("admin1");
		ids.add("user2");
		
		mRepo.findAllById(ids).forEach(member -> {
			member.setMpassword(passEncoder.encode("1234"));
			mRepo.save(member);
		});;
	}
}
