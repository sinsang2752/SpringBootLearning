package com.shinhan.firstzone.service;

import java.util.List;

import com.shinhan.firstzone.vo2.GuestBookDTO;
import com.shinhan.firstzone.vo2.GuestBookEntity;

public interface GuestBookService {

	// CRUD
	
	
	// 1.Create
	void create(GuestBookDTO dto);
	
	// 2.Read
	List<GuestBookDTO> readAll();
	
	GuestBookDTO readById(Long gno);
	
	List<GuestBookDTO> getSearch(String type, String keyword);
	
	// 3.Update
	void update(GuestBookDTO dto);
	
	// 4.Delete
	void delete(Long gno);
	
	// Entity -> DTO
	default GuestBookDTO entityToDTO(GuestBookEntity entity) {
		return GuestBookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
	}
	
	// DTO -> Entity
	default GuestBookEntity dtoToEntity(GuestBookDTO dto) {
		return GuestBookEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
	}
}
