package com.shinhan.firstzone.vo3;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"board"})
@EqualsAndHashCode(of = {"rno"})
@Table(name = "t_freereply")
@Entity
public class FreeReplyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String reply;	//댓글 내용
	private String replyer;
	
	@CreationTimestamp	//insert시에 자동
	private Timestamp regdate;
	
	@UpdateTimestamp	//insert, update시 자동
	private Timestamp updatedate;
	
	// 하나의 board에 댓글이 여러개 있다.(자식)
	@ManyToOne (fetch = FetchType.LAZY)
	private FreeBoardEntity board;
}
