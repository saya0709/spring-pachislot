package com.example.demo.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MahoikuRequest implements Serializable {
	
	//ここから通常時	
	  /**
	   * 稼働日
	   */
	  @NotEmpty(message = "稼働日を入力してください")
	  @Pattern(regexp="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$" ,message = "yyyy-MM-ddの形式で入力してください")
	  private String date;
	  
	  //枠
//	  @NotEmpty(message = "枠色を入力してください")
	  private String frame;
	  
	  
	  /**
	   * ゲーム数
	   */
	  @NotNull(message = "ゲーム数を入力してください")
	  @Range(max = 10000, message = "１万ゲーム未満を入力してください")
	  private int totalGameNum;
	  
	  /**
	   * 通常時ゲーム数
	   */
	  @NotNull(message = "通常ゲーム数を入力してください")
	  @Range(max = 10000, message = "１万ゲーム未満を入力してください")
	  private int gameNum;
	  
	  /**
	   * ARTゲーム数
	   */
	  @NotNull(message = "ARTゲーム数を入力してください")
	  @Range(max = 10000, message = "１万ゲーム未満を入力してください")
	  private int artGameNum;
	  
	  /**
	   * 同色ボーナス
	   */
	  @NotNull(message = "同色BIG数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int SBIG;
	  
	  /**
	   * 異色ボーナス
	   */
	  @NotNull(message = "異色BIG数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int BIG;
	  
	  /**
	   * REGボーナス
	   */
	  @NotNull(message = "REG数を入力してください")
	  @Range(max = 100, message = "100未満を入力してください")
	  private int REG;
	  	  
	  
	  /**
	   * まほいくとらいある
	   */
	  @NotNull(message = "まほいくとらいある突入回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int trial;
	  
	  /**
	   * まほいくとらいある成功
	   */
	  @NotNull(message = "まほいくとらいある成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int trialSuccess;
	  
	  /**
	   * episode
	   */
	  @NotNull(message = "EPISODE回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int episode;
	  
	  /**
	   * 戦闘狂BATTLE
	   */
	  @NotNull(message = "戦闘狂BATTLE突入回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int battle;
	  
	  /**
	   * SUPER戦闘狂BATTLE
	   */
	  @NotNull(message = "SUPER戦闘狂BATTLE回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int superBattle;
	  
	  
	  /**
	   * SURVIVAL ZONE
	   */
	  @NotNull(message = "SURVIVAL ZONE突入回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int survival;
	  
	  /**
	   * RAGING ZONE
	   */
	  @NotNull(message = "RAGING ZONE回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int raging;
	  
	  
	  
//ここまで
	  
//ここからビタ
	  /**
	   * ARTビタ
	   */
	  @NotNull(message = "ARTビタ回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bita;
	  /**
	   * ARTビタ成功回数
	   */
	  @NotNull(message = "ARTビタ成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bitaSuccess;
	  /**
	   * ARTビタ救済回数
	   */
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int relief;
	  
	  
	  /**
	   * BIG中ビタ
	   */
	  @NotNull(message = "BIG中ビタ回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bigBita;
	  /**
	   * BIG中ビタ成功回数
	   */
	  @NotNull(message = "BIG中ビタ成功回数を入力してください")
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bigBitaSuccess;
	  /**
	   * BIG中ビタ救済回数
	   */
	  @Range(max = 10000, message = "10000未満を入力してください")
	  private int bigRelief;
	  
	  
	  
//ここまで

	  
	

}
