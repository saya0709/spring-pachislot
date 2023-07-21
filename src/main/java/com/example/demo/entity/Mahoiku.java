package com.example.demo.entity;

import lombok.Data;

@Data
public class Mahoiku {
	private Integer mahoiku_id;

	private String year;
	private String month;
	private String day;
	
	private String frame;
	  
	
	private Integer totalGameNum;
	private Integer gameNum;
	private Integer artGameNum;
	private Integer SBIG;
	private Integer BIG;
	private Integer REG;
	
	private Integer trial;
	private Integer trialSuccess;
	private Integer episode;
	private Integer battle;
	private Integer superBattle;
	private Integer survival;
	private Integer raging;
	
	
	
	private Integer bita;
	private Integer bitaSuccess;
	
	private Integer relief;
	private Integer bigBita;
	private Integer bigBitaSuccessNum;
	private Integer bigRelief;



}
