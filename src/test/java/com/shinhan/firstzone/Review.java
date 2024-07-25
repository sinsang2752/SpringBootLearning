package com.shinhan.firstzone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.MemberRespository;
import com.shinhan.firstzone.repository.PDSBoardRepository;
import com.shinhan.firstzone.repository.PDSFileRepository;
import com.shinhan.firstzone.repository.ProfileRespository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Review {

	// ManyToOne Test
	@Autowired
	MemberRespository mRepo;
	@Autowired
	ProfileRespository pRepo;
	
	// OneToMany Test
	@Autowired
	PDSBoardRepository boardRepo;
	@Autowired
	PDSFileRepository fileRepo;
	
	// OneToMany Test
	@Transactional	//Lazy 이더라도 같은 transaction으로 묶음 fetch가 즉시로딩으로 수행됨
	@Test
	void f2() {
		boardRepo.findAll().forEach(board -> {
			System.out.println(board);		//즉시로딩(Eager), 지연로딩(Lazy) default
		});
	}
	
	// ManyToOne Test
	//@Test
	void f1() {
		pRepo.findAll().forEach(profile -> {
			System.out.println(profile);
		});
	}
}
