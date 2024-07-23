package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.firstzone.vo.BoardEntity;

public interface BoardRepository extends CrudRepository<BoardEntity, Long>
									, PagingAndSortingRepository<BoardEntity, Long>{
	
	// 1. 기본CRUD는 제공: findAll(), findById(), save(), count(), deleteById()
	// 2. 규칙에 맞는 함수를 정의
	// https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#jpa.query-methods
	
	List<BoardEntity> findByWriter(String writer);
	
	List<BoardEntity> findByTitleStartingWith(String title);
	
	List<BoardEntity> findByTitleEndingWith(String title);
	
	List<BoardEntity> findByTitleContaining(String title);
	
	List<BoardEntity> findByBnoBetween(long firstBno, long secondBno);
	
	List<BoardEntity> findByBnoBetweenAndContentContainingOrderByWriter(long firstBno, long secondBno, String Title);
	
	// paging 추가
	// where bno > ? order by bno desc
	List<BoardEntity> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable pageable);
	
	// 3.JPQL(JPA Query Language) 이용하기: 복잡한 SQL 문장 생성 시 이용
	// native Query와 비슷하지만 같지는 않다, 예, select * from (*불가)
	@Query("select b from BoardEntity b "
			+ "where b.title like %?1% and b.content like %?2% order by bno desc")
	List<BoardEntity> jpqlTest1(String title, String content);
	
	@Query("select b from BoardEntity b "
			+ "where b.title like %:tt% and b.content like %:cc% order by bno desc")
	List<BoardEntity> jpqlTest2(@Param("tt") String title, @Param("cc") String content);
	
	@Query("select b from #{#entityName} b "
			+ "where b.title like %:tt% and b.content like %:cc% order by bno desc")
	List<BoardEntity> jpqlTest3(@Param("tt") String title, @Param("cc") String content);
	
	@Query("select b.title, b.content, b.writer from #{#entityName} b "
			+ "where b.title like %:tt% and b.content like %:cc% order by bno desc")
	List<Object[]> jpqlTest4(@Param("tt") String title, @Param("cc") String content);
	
	
	// nativeQuery 
	@Query(value = "select * from t_board "
			+ "where title like %?1% and content like %?2% order by bno desc", nativeQuery = true)
	List<BoardEntity> jpqlTest5(String title, String content);
}
