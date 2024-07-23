package com.shinhan.firstzone.vo2;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@MappedSuperclass	//table 생성은 안된다.
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "regdate", updatable = false) 		// updateable = false 수정을 못한다.
	private LocalDateTime regDate;

	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;
	
}
