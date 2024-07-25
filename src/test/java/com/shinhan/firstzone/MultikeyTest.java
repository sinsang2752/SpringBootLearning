package com.shinhan.firstzone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository3.MultiKeyARepository;
import com.shinhan.firstzone.repository3.MultiKeyBRepository;
import com.shinhan.firstzone.vo3.MultiKeyAClass;
import com.shinhan.firstzone.vo3.MultiKeyB;
import com.shinhan.firstzone.vo3.MultiKeyBClass;

@SpringBootTest
public class MultikeyTest {

	@Autowired
	MultiKeyARepository aRepo;
	
	@Autowired
	MultiKeyBRepository bReop;
	
	@Test
	void selectB() {
		MultiKeyB id = MultiKeyB.builder()
				.id1(10)
				.build();
		
		bReop.findById(id).ifPresent(data -> {
			System.out.println(data);
		});
	}
	
	//@Test
	void insertB() {
		MultiKeyB id = MultiKeyB.builder()
				.id1(10)
				.id2(20)
				.build();
		MultiKeyBClass b = MultiKeyBClass.builder()
			.multib(id)
			.productName("아이스 아메리카노")
			.price(1500)
			.build();
		bReop.save(b);
	}
	
	//@Test
	void selectA() {
		
	}
	
	//@Test
	void insert() {
		MultiKeyAClass a = MultiKeyAClass.builder()
			.id1(10)
			.id2(20)
			.productName("아이스 아메리카노")
			.price(1500)
			.build();
		aRepo.save(a);
	}
}
