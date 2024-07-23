package com.shinhan.firstzone.vo2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GuestBookDTO {
	Long gno;
	String title;
	String content;
	String writer;
	LocalDateTime regDate, modDate;
}
