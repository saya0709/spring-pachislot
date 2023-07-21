package com.example.demo.entity;

import lombok.Data;

@Data
public class Kamaitachi {
	
	private Integer kamaitachi_id;

	private String year;
	private String month;
	private String day;
	private Integer gameNum;
	private Integer SBIG;
	private Integer BIG;
	private Integer REG;
	private Integer beru;
	private Integer suika;
	private Integer cherry;
	private Integer ichimaiyakuA;
	private Integer ichimaiyakuBC;
	private Integer even;
	private Integer odd;
	private Integer silhouette;
	private Integer nomal;
	
	private Integer bitaSuccessNum;
	private Integer bitaFailureNum;
	private Integer tenrakuBitaSuccessNum;
	private Integer tenrakuBitaFailureNum;
	private Integer rtNum;
	

}
