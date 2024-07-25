package com.shinhan.firstzone.repository3;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.firstzone.vo3.FreeBoardEntity;

public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Long>
																, PagingAndSortingRepository<FreeBoardEntity, Long>{

	@Query("select b.title, count(r) " 
			+ " from #{#entityName} b left outer join b.reply r "
			+ " group by b.title order by b.title ")
	public List<Object[]> getBoardReplyCount2();
	
	Page<FreeBoardEntity> findByBnoBetween(Long bno1, Long bno2, Pageable pageable);
	
}
