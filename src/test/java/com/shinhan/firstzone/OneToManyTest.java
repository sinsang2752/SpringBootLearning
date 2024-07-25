package com.shinhan.firstzone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.shinhan.firstzone.repository.PDSBoardRepository;
import com.shinhan.firstzone.repository.PDSFileRepository;
import com.shinhan.firstzone.vo2.PDSBoardEntity;
import com.shinhan.firstzone.vo2.PDSFileEntity;

import jakarta.transaction.Transactional;

@Commit
@SpringBootTest
public class OneToManyTest {

	@Autowired
	PDSFileRepository fileRepo;

	@Autowired
	PDSBoardRepository boardRepo;

	
	@Test
	void deleteBoard() {
		boardRepo.deleteById(11L);
	}
	
	//@Test
	void deleteFile() {
		fileRepo.deleteById(50L);
	}
	
	// @Transactional 추가하기, 실행 후 Test환경은 ROLLBACK 처리된다. class level에 @Commit 추가
	//@Test
	//@Transactional
	void updateFile() {
		boardRepo.updatePDSFile(50L, "파일 이름 수정");
	}
	
	//@Test
	void updateName() {
		boardRepo.findAll().forEach(board -> {
			board.setPname(board.getPname() + "-" + board.getPid());
			boardRepo.save(board);
		});
	}
	
	//@Test
	void selectBoard5() {	
		boardRepo.getFilesCount2().forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	
	//@Test
	void selectBoard4() {
		long pid = 4L;
		List<Object[]> blist = boardRepo.getFilesInfo(pid);
		blist.forEach(board -> {
			System.out.println(board[0]);
		});
	}
	
	//@Test
	void selectBoard2() {
		String w = "수영1";
		List<PDSBoardEntity> blist = boardRepo.findByWriter(w);

		for (PDSBoardEntity board : blist) {
			System.out.println(board.getPname());
		}
	}

	// 조회
	// PDSFileEntity의 pdsfilename으로 조회
	//@Test
	void selectFile2() {
		String pdsfilename = "스프링Book-2.ppt";

		fileRepo.findByPdsFileName(pdsfilename).forEach(file -> {
			System.out.println(file);
		});
	}

	// @Test
	void selectBoard() {
		Long pid = 2L;
		PDSBoardEntity board = boardRepo.findById(pid).orElse(null);

		if (board == null)
			return;

		System.out.println(board);
		System.out.println(board.getPname());
		System.out.println(board.getWriter());
		System.out.println(board.getFiles().size());
		System.out.println(board.getFiles());
	}

	// @Test
	void selectFile() {
		Long fno = 10L;
		PDSFileEntity file = fileRepo.findById(fno).orElse(null);
		System.out.println(file);
	}

	// @Test
	void insert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {

			List<PDSFileEntity> files = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j -> {
				PDSFileEntity entity = PDSFileEntity.builder().pdsFileName("스프링Book-" + j + ".ppt").build();

				files.add(entity);
			});

			PDSBoardEntity board = PDSBoardEntity.builder().pname("Python교육").writer("수영" + i).files(files).build();

			boardRepo.save(board);
		});
	}
}
