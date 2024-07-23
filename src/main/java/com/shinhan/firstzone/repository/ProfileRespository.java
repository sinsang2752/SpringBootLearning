package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.firstzone.vo2.GuestBookEntity;
import com.shinhan.firstzone.vo2.MemberEntity;
import com.shinhan.firstzone.vo2.ProfileEntity;

public interface ProfileRespository  extends CrudRepository<ProfileEntity, Long>
																, PagingAndSortingRepository<ProfileEntity, Long>
																, QuerydslPredicateExecutor<ProfileEntity>{

	List<ProfileEntity> findByMemberAndCurrentYnTrue(MemberEntity member);
	
	@Query(value = "select m.mname, p.fname from ProfileEntity p "
			+ "right outer join MemberEntity m on (m = p.member) where m.mid = ?1")
	List<Object[]> getProfile(String mid);
	
	@Query("select m.mid, count(p) from MemberEntity m "
			+ " left outer join ProfileEntity p on (m = p.member) group by  m.mid")
	List<Object[]> getPrifileCount();
}
