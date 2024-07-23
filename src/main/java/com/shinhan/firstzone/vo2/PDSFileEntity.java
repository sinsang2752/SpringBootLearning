package com.shinhan.firstzone.vo2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 하나의 Board에 첨부 File이 여러개있다.
@Table(name = "t_pdsfile")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PDSFileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long fno;
	String pdsFileName;
}
