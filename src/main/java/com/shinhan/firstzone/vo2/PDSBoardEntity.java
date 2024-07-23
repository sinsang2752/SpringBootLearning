package com.shinhan.firstzone.vo2;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "t_pdsboard")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PDSBoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pid;
	String pname;
	String writer;
	
	// cascade: 전의, 나의 변화가 나와 연관관계가 있는 곳에 전이
	@OneToMany(cascade = CascadeType.ALL,
						fetch = FetchType.EAGER)
	@JoinColumn(name = "pdsno")	//PDSFileEntity의 테이블에  pdsno컬럼이 생성된다.
	// join 칼럼이 생략되면 중간테이블이 생성된다.
	List<PDSFileEntity> files;
}
