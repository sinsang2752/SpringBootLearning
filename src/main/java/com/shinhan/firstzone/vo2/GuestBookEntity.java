package com.shinhan.firstzone.vo2;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "t_guestbook")
@Entity	// JPA가 관리하는 객체(DB의 객체와 자바의 객체를 Mapping)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GuestBookEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno;
	
	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 2000)
	private String content;

	@Column(length = 50)
	private String writer;

	@Override
	public String toString() {
		return "GuestBookEntity [gno=" + gno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", getRegDate()=" + getRegDate() + ", getModDate()=" + getModDate() + "]";
	}
	
	

}
