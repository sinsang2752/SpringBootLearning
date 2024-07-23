package com.shinhan.firstzone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.firstzone.repository.BoardRepository;
import com.shinhan.firstzone.vo.BoardEntity;
import com.shinhan.firstzone.vo.CarVO;



@RestController		// @Controller + @ResponseBody = @RestController
@RequestMapping("/sample")
public class SampleRestController {

	@Autowired
	BoardRepository bRepo;
	
	@PutMapping("/update")
	public Long update(@RequestBody BoardEntity board) {
		
		BoardEntity newBoard = bRepo.save(board);
		return newBoard.getBno();
	}
	
	@PostMapping("/insert")
	public Long insert(@RequestBody BoardEntity board) {
		BoardEntity newBoard = bRepo.save(board);
		return newBoard.getBno();
	}
	
	@GetMapping("/detail/{bno}")
	public BoardEntity detail(@PathVariable long bno) {
		return (BoardEntity) bRepo.findById(bno).orElse(null);
	}
	
	@GetMapping("/list")
	public List<BoardEntity> list() {
		return (List<BoardEntity>) bRepo.findAll();
	}
	
	@GetMapping("/test1")
	public String f1() {
		return "Hello~~~ 반갑습니다.";
	}
	
	@GetMapping("/test2")
	public CarVO f2() {
		CarVO car = CarVO.builder()
				.model("ABC 모델")
				.price(2000)
				.build();
		return car;
	}
	
	@GetMapping("/test3")
	public List<CarVO> f3() {
		List<CarVO> carList = new ArrayList<>();
		
		IntStream.rangeClosed(1, 5).forEach(i -> {
			CarVO car = CarVO.builder()
					.model("ABC 모델" + i)
					.price(2000 * i)
					.build();
			carList.add(car);
		});
		
		return carList;
	}
}
