package com.shinhan.firstzone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository3.UserCellPhoneVORepository;
import com.shinhan.firstzone.repository3.UserCellPhoneVORepository2;
import com.shinhan.firstzone.repository3.UserVO3Repository;
import com.shinhan.firstzone.repository3.UserVORepository;
import com.shinhan.firstzone.vo3.QUserCellPhoneVO2;
import com.shinhan.firstzone.vo3.UserCellPhoneVO;
import com.shinhan.firstzone.vo3.UserCellPhoneVO2;
import com.shinhan.firstzone.vo3.UserCellPhoneVO3;
import com.shinhan.firstzone.vo3.UserVO;
import com.shinhan.firstzone.vo3.UserVO2;
import com.shinhan.firstzone.vo3.UserVO3;

@SpringBootTest
public class OneToOneTest {

	@Autowired
	UserVORepository uRepo;
	
	@Autowired
	UserCellPhoneVORepository pRepo;
	
	@Autowired
	UserCellPhoneVORepository2 p2Repo;
	
	@Autowired
	UserVO3Repository u3Repo;
	
	@Test
	void f3() {
		
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
				.phoneNumber("12345")
				.model("aa")
				.build();
		
		UserVO3 user = UserVO3.builder()
				.userid("옥")
				.username("옥주현")
				.phone(phone)
				.build();
		phone.setUser3(user);
		u3Repo.save(user);
	}
	
	//@Test
	void f2() {
		UserVO2 user = UserVO2.builder()
				.userid("coffee")
				.username("우지")
				.build();
		
		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("010-1234-1111")
				.model("아이폰")
				.user(user)
				.build();
		
		p2Repo.save(phone);
	}
	
	//@Test
	void f1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder()
				.phoneNumber("010-9290-2752")
				.model("아이폰")
				.build();
		UserCellPhoneVO newPhone = pRepo.save(phone);
		// phone이 먼저 저장 후 사용 가능
		UserVO user = UserVO.builder()
				.userid("sinsang")
				.username("현상")
				.phone(newPhone)
				.build();
		uRepo.save(user);
	}
}
