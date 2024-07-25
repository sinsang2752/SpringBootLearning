package com.shinhan.firstzone.repository3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinhan.firstzone.vo3.MultiKeyB;
import com.shinhan.firstzone.vo3.MultiKeyBClass;

public interface MultiKeyBRepository extends JpaRepository<MultiKeyBClass, MultiKeyB>{

}
