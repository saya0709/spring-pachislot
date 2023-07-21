package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class KamaitachiMonth {
		//ここから通常時	
		  /**
		   * 稼働日
		   */
		  @NotEmpty(message = "稼働月を選択してください")
		  private String date;
		  
		  

}
