package com.shinhan.firstzone.vo3;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultiKeyA implements Serializable{
	Integer id1;
	Integer id2;
}
