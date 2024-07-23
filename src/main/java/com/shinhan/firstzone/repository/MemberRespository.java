package com.shinhan.firstzone.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.firstzone.vo2.GuestBookEntity;
import com.shinhan.firstzone.vo2.MemberEntity;
import com.shinhan.firstzone.vo2.ProfileEntity;

public interface MemberRespository  extends CrudRepository<MemberEntity, String>
																, PagingAndSortingRepository<MemberEntity, String>
																, QuerydslPredicateExecutor<MemberEntity>{

}
