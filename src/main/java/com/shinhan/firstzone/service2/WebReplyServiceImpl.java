package com.shinhan.firstzone.service2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.firstzone.repository4.WebReplyRepository;
import com.shinhan.firstzone.vo4.WebBoardEntity;
import com.shinhan.firstzone.vo4.WebReplyDTO;
import com.shinhan.firstzone.vo4.WebReplyEntity;

@Service
public class WebReplyServiceImpl implements WebReplyService{

	@Autowired
	WebReplyRepository replyReop;
	
	@Override
	public Long register(WebReplyDTO dto) {
		WebReplyEntity newReply = replyReop.save(dtoToEntity(dto));
		return newReply.getRno();
	}

	@Override
	public List<WebReplyDTO> getList(Long bno) {
		WebBoardEntity board = WebBoardEntity.builder().bno(bno).build();
		List<WebReplyEntity> entityList = replyReop.findByBoard(board);
		return entityList.stream()
				.map(en -> entityToDTO(en))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(WebReplyDTO dto) {
		replyReop.findById(dto.getRno()).ifPresent(reply -> {
			reply.setReply(dto.getReply());
			reply.setReplyer(dto.getReplyer());
			replyReop.save(reply);
		});
	}

	@Override
	public void delete(Long rno) {
		replyReop.deleteById(rno);
	}

}
