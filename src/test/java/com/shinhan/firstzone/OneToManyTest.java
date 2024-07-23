package com.shinhan.firstzone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.PDSBoardRepository;
import com.shinhan.firstzone.repository.PDSFileRepository;
import com.shinhan.firstzone.vo2.PDSBoardEntity;
import com.shinhan.firstzone.vo2.PDSFileEntity;

@SpringBootTest
public class OneToManyTest {

	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	
	@Test
	void selectBoard() {
		Long pid = 2L;
		PDSBoardEntity board = boardRepo.findById(pid).orElse(null);
		
		if(board == null) return;
		
		System.out.println(board);
		System.out.println(board.getPname());
		System.out.println(board.getWriter());
		System.out.println(board.getFiles().size());
		System.out.println(board.getFiles());
	}
	
	//@Test
	void selectFile() {
		Long fno = 10L;
		PDSFileEntity file = fileRepo.findById(fno).orElse(null);
		System.out.println(file);
	}
	
	
	//@Test
	void insert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			List<PDSFileEntity> files = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j -> {
				PDSFileEntity entity =  PDSFileEntity.builder()
				.pdsFileName("스프링Book-" + j + ".ppt")
				.build();
				
				files.add(entity);
			});
			
			PDSBoardEntity board = PDSBoardEntity.builder()
					.pname("Python교육")
					.writer("수영" + i)
					.files(files)
					.build();
			
			boardRepo.save(board);
		});
	}
}
