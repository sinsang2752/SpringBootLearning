package com.shinhan.firstzone.repository3;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.firstzone.vo3.MultiKeyA;
import com.shinhan.firstzone.vo3.MultiKeyAClass;

public interface MultiKeyARepository extends JpaRepository<MultiKeyAClass, MultiKeyA>{

}
