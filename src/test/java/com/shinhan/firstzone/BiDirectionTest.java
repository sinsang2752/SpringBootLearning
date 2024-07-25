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

import com.shinhan.firstzone.repository3.FreeBoardRepository;
import com.shinhan.firstzone.repository3.FreeReplyRepository;
import com.shinhan.firstzone.vo3.FreeBoardEntity;
import com.shinhan.firstzone.vo3.FreeReplyEntity;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BiDirectionTest {

	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeReplyRepository replyRepo;

	// 조건조회 bno >= 10 and bno <=20, paging추가, sort추가
	@Test
	void f1() {
		Pageable pageable = PageRequest.of(1, 5, Sort.Direction.DESC, "bno");
		
		Page<FreeBoardEntity> result = boardRepo.findByBnoBetween(1L, 10L, pageable);
		System.out.println("getNumber: " + result.getNumber());
		System.out.println("getSize: " + result.getSize());
		System.out.println("getTotalElements: " + result.getTotalElements());
		System.out.println("getTotalPages: " + result.getTotalPages());
		System.out.println("getSort: " + result.getSort());
		System.out.println("getPageable: " + result.getPageable());
		result.forEach(board -> {
			System.out.println(board);
			System.out.println(board.getReply().size() + "건");
		});
	}
	
	
	//@Test
	void selectBoardReply() {
		boardRepo.getBoardReplyCount2().forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//@Test
	void selectBoard() {
		boardRepo.findAll().forEach(board -> {
			System.out.println(board);
			System.out.println("댓글 건 수: " + board.getReply().size());
		});
	}
	
	//조회 (Board 번호를 알고 댓글들의 정보를 조회)
	//@Test
	//@Transactional
	void selectByReply() {
		FreeBoardEntity bno = FreeBoardEntity.builder().bno(3L).build();
		
		replyRepo.findByBoard(bno).forEach(r -> {
			System.out.println(r);
			System.out.println(r.getBoard());
		});
		
	}
	
	//댓글 수정
	//@Test
	void updateReply() {
		FreeBoardEntity board = FreeBoardEntity.builder().bno(4L).build();
		replyRepo.findById(1L).ifPresent(reply -> {
			reply.setBoard(board);
			replyRepo.save(reply);
		});
	}
	
	//댓글 삽입
	//@Test
	void insertReply() {
		FreeBoardEntity board = FreeBoardEntity.builder().bno(4L).build();
		FreeReplyEntity reply = FreeReplyEntity.builder()
				.reply("점심메뉴")
				.replyer("user1")
				.board(board)
				.build();
		replyRepo.save(reply);
	}
	
	//@Test
	void insertBoard2() {
		boardRepo.findById(3L).ifPresent(board -> {
			List<FreeReplyEntity> replyList = board.getReply();
			IntStream.rangeClosed(1, 10).forEach(i -> {
				FreeReplyEntity reply = FreeReplyEntity.builder()
						.reply("맛집~~" + i)
						.replyer("user" + i%2)
						.board(board)
						.build();
				replyList.add(reply);
			});
			boardRepo.save(board);
		});
	}
	
	//@Test
	void insertBoard() {
		// 30건
		IntStream.rangeClosed(1, 30).forEach(i -> {
			FreeBoardEntity entity = FreeBoardEntity.builder()
					.title("수요일" + i)
					.content("오늘 날씨는 " + (i%2 == 0?"비":"맑음"))
					.writer("user" + i%5)
					.build();
			
			boardRepo.save(entity);
		});
	}
}
