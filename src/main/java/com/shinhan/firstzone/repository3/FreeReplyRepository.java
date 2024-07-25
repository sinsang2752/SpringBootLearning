package com.shinhan.firstzone.repository3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.firstzone.vo3.FreeBoardEntity;
import com.shinhan.firstzone.vo3.FreeReplyEntity;

public interface FreeReplyRepository extends JpaRepository<FreeReplyEntity, Long>{

	List<FreeReplyEntity> findByBoard(FreeBoardEntity board);
}
