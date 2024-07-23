package com.shinhan.firstzone;

import static org.mockito.ArgumentMatchers.endsWith;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.shinhan.firstzone.repository.GuestBookRepository;
import com.shinhan.firstzone.repository.MemberRespository;
import com.shinhan.firstzone.repository.ProfileRespository;
import com.shinhan.firstzone.service.GuestBookService;
import com.shinhan.firstzone.service.GuestBookServiceImpl;
import com.shinhan.firstzone.vo2.GuestBookDTO;
import com.shinhan.firstzone.vo2.GuestBookEntity;
import com.shinhan.firstzone.vo2.MemberEntity;
import com.shinhan.firstzone.vo2.MemberRole;
import com.shinhan.firstzone.vo2.ProfileEntity;
import com.shinhan.firstzone.vo2.QGuestBookEntity;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestBookTest {

	@Autowired
	GuestBookRepository gRepo;
	
	@Autowired
	GuestBookServiceImpl gService;
	
	@Autowired
	ProfileRespository pRepo;
	
	@Autowired
	MemberRespository mRepo;
	
	
	@Test
	void getProfileCount() {
		pRepo.getPrifileCount().forEach(profile -> {
			log.info(Arrays.toString(profile));
			log.info("mname: " + profile[0] + " 프로파일 갯수: " + profile[1]);
			log.info("-----------------------------------");
		});
	}
	
	//@Test
	void customGetId() {
		pRepo.getProfile("user5").forEach(profile -> {
			log.info(Arrays.toString(profile));
		});
	}
	
	//@Test
	void selectByMemberAndCurrentYnTrue() {
		MemberEntity member =  mRepo.findById("user5").orElse(null);
		pRepo.findByMemberAndCurrentYnTrue(member).forEach(profile -> {
			log.info(profile);
		});;
	}
	
	// 1. Member insert
    // @Test
    void memberInsert() {
        // 규칙 없으면 loop 돌지 않아도 됨
        IntStream.rangeClosed(1, 5).forEach(i -> {
            /*
            MemberEntity member = MemberEntity.builder()
                                                .mid(i == 1 ? "king" : "user" + i)
                                                .mname("황수영" + i)
                                                .mpassword("1234")
                                                .mrole(i == 1 ? MemberRole.MANAGER : MemberRole.USER)
                                                .build();
            */
            
            MemberEntity member = MemberEntity.builder()
                    .mid("user" + i)
                    .mname("설기" + i)
                    .mpassword("1234")
                    .mrole(MemberRole.ADMIN)
                    .build();
            
            mRepo.save(member);
        });
    }
	
     //@Test
    void profileInsert() {
        MemberEntity member = MemberEntity.builder().mid("user5").build();
        
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ProfileEntity profile = ProfileEntity.builder()
                                          .fname("face-" + 1)
                                          .currentYn(i == 5 ? true : false)
                                          .member(member) // admin1의 profile
                                          .build();
            
            pRepo.save(profile);
        });
    }
	
	//@Test
	void f6() {
		pRepo.findById(1L).ifPresentOrElse(profile -> {
			log.info("파일 이름: " + profile.getFname());
			log.info(profile.isCurrentYn() ? "현재 프로파일": "과거 프로파일");
			log.info("이름" + profile.getMember().getMname());
			log.info("권한" + profile.getMember().getMrole().name());
		}, () -> {
			log.info("존재하지 않음");
		});
	}
	
	//@Test
	void f5() {
		String type = "tw";
		String keyword = "요일";
		
		String[] arr = type.split("");
		BooleanBuilder builder = new BooleanBuilder();
		
		QGuestBookEntity entity = QGuestBookEntity.guestBookEntity;
		
		BooleanExpression expression = entity.gno.gt(0L);	// gno > 0
		builder.and(expression);
		
		BooleanBuilder builder2 = new BooleanBuilder();
		
		if(type.contains("t")) {
			builder2.or(entity.title.contains(keyword));
		}
		
		if(type.contains("c")) {
			builder2.or(entity.content.contains(keyword));
		}
		
		if(type.contains("w")) {
			builder2.or(entity.writer.contains(keyword));
		}
		
		builder.and(builder2);
		
		System.out.println(builder);
		
		gRepo.findAll(builder).forEach(aa -> {
			log.info(aa);
		});
	}
	
	//@Test
	void f2() {
		String aa = "요일";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QGuestBookEntity book = QGuestBookEntity.guestBookEntity;
		
		builder.and(book.title.like("%" + aa + "%"));
	}
	
	//@Test
	void f1() {
		GuestBookEntity entity = gRepo.findById(8L).orElse(null);
		
		if(entity != null) {
			GuestBookDTO dto = GuestBookDTO.builder()
					.gno(entity.getGno())
					.title(entity.getTitle())
					.content(entity.getContent())
					.writer(entity.getWriter())
					.regDate(entity.getRegDate())
					.modDate(entity.getModDate())
					.build();
			
			log.info(dto);
		}
	}
	
	//@Test
	void isNull() {
		gRepo.findByRegDateNull().forEach(entity -> {
			log.info(entity);
		});
	}
	
	//@Test
	void select() {
		gRepo.findAll().forEach(entity -> {
			log.info(entity);
		});
	}
	
	//@Test
	void insert() {
		IntStream.range(11, 15).forEach(i -> {
			GuestBookEntity entity = GuestBookEntity.builder()
					.title("화요일@@@" + i)
					.content("배고파~~")
					.writer("사용자")
					.build();
			gRepo.save(entity);
		});
	}
}
