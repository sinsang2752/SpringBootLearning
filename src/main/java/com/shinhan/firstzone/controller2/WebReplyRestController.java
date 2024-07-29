package com.shinhan.firstzone.controller2;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.firstzone.service2.WebReplyService;
import com.shinhan.firstzone.vo4.WebReplyDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Tag(name="댓글", description = "여기에서는 WebReply CRUD 작성됨")
public class WebReplyRestController {

	final WebReplyService replyService;
	
	@Tag(name="댓글 리스트", description = "댓글 조회")
	@GetMapping("/list/{bno}")
	List<WebReplyDTO> list(@PathVariable("bno") Long bno) {
		return replyService.getList(bno);
	}
	
	@Tag(name="댓글 등록", description = "댓글 등록")
	@PostMapping("/register")
	Long insert(@RequestBody WebReplyDTO dto) {
		return replyService.register(dto);
	}
	
	@Tag(name="댓글 수정", description = "댓글 수정")
	@PutMapping("/modify")
	String modify(@RequestBody WebReplyDTO dto) {
		replyService.modify(dto);
		return "success";
	}
	
	@Tag(name="댓글 삭제", description = "댓글 삭제")
	@DeleteMapping("/delete/{rno}")
	String delete(@PathVariable Long rno) {
		replyService.delete(rno);
		return "success";
	}
}
