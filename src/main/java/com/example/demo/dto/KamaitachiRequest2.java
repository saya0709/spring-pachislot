package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KamaitachiRequest2 {
	//ここから通常時	
	  /**
	   * 稼働日
	   */
	  private String year;
	  private String month;
	  private String day;
	  
	  /**
	   * ゲーム数
	   */
	  @NotNull(message = "ゲーム数を入力してください")
	  @Range(max = 10000, message = "１万ゲーム未満を入力してください")
	  private int gameNum;

	  
	  /**
	   * かまいたちボーナス
	   */
	  @NotNull(message = "かまいたちボーナス数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int SBIG;
	  
	  /**
	   * BIGボーナス
	   */
	  @NotNull(message = "BIGボーナス数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int BIG;
	  
	  /**
	   * REGボーナス
	   */
	  @NotNull(message = "REGボーナス数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int REG;
	  
	  /**
	   * ベル
	   */
	  @NotNull(message = "ベルの出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int beru;
	  
	  /**
	   * スイカ
	   */
	  @NotNull(message = "スイカの出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int suika;
	  
	  /**
	   * チェリー
	   */
	  @NotNull(message = "チェリーの出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int cherry;
	  
	  /**
	   * 一枚役A
	   */
	  @NotNull(message = "一枚役Aの出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int ichimaiyakuA;
	  
	  /**
	   * 一枚役BC
	   */
	  @NotNull(message = "一枚役B,Cの出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int ichimaiyakuBC;
	  
	  
	  /**
	   * 偶数示唆回数
	   */
	  @NotNull(message = "偶数示唆の出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int even;
	  
	  /**
	   * 奇数示唆回数
	   */
	  @NotNull(message = "奇数示唆の出現回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int odd;
	  
	  
	  /**
	   * BIG中一枚絵シルエット回数
	   */
	  @NotNull(message = "BIG中一枚絵シルエット回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int silhouette;
	  
	  /**
	   * BIG中一枚絵ノーマル回数
	   */
	  @NotNull(message = "BIG中一枚絵シルエット回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int nomal;
	  
	  
	  
	  
	  
//ここまで
	  
//ここからビタ
	  /**
	   * RTビタ成功回数
	   */
	  @NotNull(message = "RTビタ成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bitaSuccessNum;
	  
	  /**
	   * RTビタ失敗回数
	   */
	  @NotNull(message = "RTビタ失敗回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bitaFailureNum;
	  
	  /**
	   * 転落ビタ成功回数
	   */
	  @NotNull(message = "転落ビタ成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int tenrakuBitaSuccessNum;
	  
	  /**
	   * 転落ビタ失敗回数
	   */
	  @NotNull(message = "転落ビタ失敗回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int tenrakuBitaFailureNum;
	  
	  /**
	   * RT突入成功回数
	   */
	  @NotNull(message = "RT突入成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int rtNum;
	  
//ここまで

	  
	  

}
