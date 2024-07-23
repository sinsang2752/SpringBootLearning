package com.shinhan.firstzone;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shinhan.firstzone.repository.BoardRepository;
import com.shinhan.firstzone.vo.BoardEntity;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardCRUDTest {

	@Autowired
	BoardRepository bRepo;

	
	@Test
	void queryTest1() {
//		bRepo.jpqlTest1("요일","짝수").forEach(board-> {log.info(board);});
//		bRepo.jpqlTest2("요일","짝수").forEach(board-> {log.info(board);});
//		bRepo.jpqlTest3("요일","짝수").forEach(board-> {log.info(board);});
//		bRepo.jpqlTest4("요일","짝수").forEach(arr-> {log.info(Arrays.toString(arr));});
		bRepo.jpqlTest5("요일","짝수").forEach(board-> {log.info(board);});
	}
	
	//@Test
	void pageable2() {
//		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "bno");
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, new String[]{"writer","bno"});
		Page<BoardEntity> result = bRepo.findAll(pageable);
		
		List<BoardEntity> blist = result.getContent();
		
		log.info("getNumber: " + result.getNumber());
		log.info("getSize: " + result.getSize());
		log.info("getTotalElements: " + result.getTotalElements());
		log.info("getTotalPages: " + result.getTotalPages());
		
		blist.forEach(board-> log.info(board));
	}
	
	//@Test
	void pageable() {
		Pageable pageable = PageRequest.of(1, 5);
		bRepo.findByBnoGreaterThanOrderByBnoDesc(10L, pageable).forEach(b -> {
			log.info(b);
		});
	}
	
	//@Test
	void custom() {
		bRepo.findByBnoBetweenAndContentContainingOrderByWriter(1, 15, "짝수") .forEach(board -> {
			log.info(board);
		});
	}
	
	//@Test
	void between() {
		bRepo.findByBnoBetween(10, 21).forEach(board -> {
			log.info(board);
		});
	}
	
	//@Test
	void containing() {
		bRepo.findByTitleContaining("일") .forEach(board -> {
			log.info(board);
		});
	}
	
	//@Test
	void endWithTitle() {
		bRepo.findByTitleStartingWith("짝수").forEach(board -> {
			log.info(board);
		});
	}
	
	//@Test
	void startWithTitle() {
		bRepo.findByTitleStartingWith("월").forEach(board -> {
			log.info(board);
		});
	}
	
	//@Test
	void selectWriter() {
		bRepo.findByWriter("user0").forEach(board -> {
			log.info(board);
		});
	}
	
	//건수확인
	//@Test
	void boardCount() {
		Long cnt = bRepo.count();
		log.info(cnt + "건");
	}
	
	// 삭제
	//@Test
	void delete() {
		bRepo.deleteById(2L);
		bRepo.findById(2L).ifPresentOrElse(board -> {
			log.info(board);
		}, () -> {
			log.info("Not Found");
		});
	}

	// @Test
	void update() {
		bRepo.findById(1L).ifPresent(board -> {
			log.info("before: " + board);
			board.setContent("~~~~~~~~수정~~~~~~~~~~");
			board.setTitle("springboot");
			board.setWriter("manager");
			BoardEntity updateBoard = bRepo.save(board);
			log.info("after: " + updateBoard);
		});
	}

	// @Test
	void detail() {
		bRepo.findById(5L).ifPresentOrElse(board -> {
			log.info(board);
		}, () -> {
			log.info("해당 데이터가 존재하지 않습니다.");
		});
	}

	// @Test
	void insert() {
		IntStream.rangeClosed(11, 20).forEach(i -> {
			BoardEntity entity = BoardEntity.builder().title("토요일" + i).content("맑음." + (i % 2 == 0 ? "짝수" : "홀수"))
					.writer("user" + i % 5).build();
			bRepo.save(entity);
		});
	}

	// @Test
	void select() {
		bRepo.findAll().forEach(board -> {
			log.info(board);
		});
	}
}
