package com.shinhan.firstzone.vo;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_board")
public class BoardEntity {
	@Id		//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	@Column(length=50, nullable = false)
	private String title;
	
	@Column(length=2000)
	private String content;
	
	private String writer;
	
	@Column(name = "regdate")
	@CreationTimestamp	//insert 시에 입력
	private Timestamp regDate;
	
	@Column(name = "updatedate")
	@UpdateTimestamp //insert 시, update 시 변경
	private Timestamp updateDate;
	
}
