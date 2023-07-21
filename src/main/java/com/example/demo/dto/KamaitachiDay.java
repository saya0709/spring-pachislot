package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class KamaitachiDay {
	/**
	 * 稼働日
	 */
	@NotEmpty(message = "稼働日を選択してください")
	private String date;



}
