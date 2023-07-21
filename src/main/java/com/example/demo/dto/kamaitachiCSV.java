package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class kamaitachiCSV implements Serializable {
	//ここから通常時	
	  /**
	   * 稼働日
	   */
	  @NotEmpty(message = "稼働日を入力してください")
	  @Pattern(regexp="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$" ,message = "yyyy-MM-ddの形式で入力してください")
	  private String date;
	  
}
