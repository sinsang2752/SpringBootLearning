package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.firstzone.vo2.GuestBookEntity;

public interface GuestBookRepository extends CrudRepository<GuestBookEntity, Long>
																		, PagingAndSortingRepository<GuestBookEntity, Long>
																		, QuerydslPredicateExecutor<GuestBookEntity>{

	List<GuestBookEntity> findByRegDateNull();
}
