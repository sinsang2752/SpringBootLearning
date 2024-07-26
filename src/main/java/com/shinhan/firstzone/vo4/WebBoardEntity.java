package com.shinhan.firstzone.vo4;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shinhan.firstzone.vo2.MemberEntity;
import com.shinhan.firstzone.vo3.FreeReplyEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "replies")
@Table(name = "t_webboard")
@Entity
public class WebBoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	
	@ManyToOne
	private MemberEntity writer;
	
	private String content;
	@CreationTimestamp	//insert시에 자동
	private Timestamp regdate;
	@UpdateTimestamp	//insert, update시 자동
	private Timestamp updatedate;
	
	@OneToMany(mappedBy = "board"
			, cascade = CascadeType.ALL
			, fetch = FetchType.EAGER)
	private List<WebReplyEntity> replies;
}
