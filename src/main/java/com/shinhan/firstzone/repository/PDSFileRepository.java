package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.firstzone.vo2.PDSFileEntity;

public interface PDSFileRepository extends JpaRepository<PDSFileEntity, Long>{
	
	List<PDSFileEntity> findByPdsFileName(String pname);
}
