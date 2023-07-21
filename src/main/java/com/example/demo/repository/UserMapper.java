package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Kamaitachi;
import com.example.demo.entity.KamaitachiYearMonth;
import com.example.demo.entity.Mahoiku;



@Mapper
public interface UserMapper {
	
//かまいたちの夜
	//稼働データ登録
	void kamaitachiInsert(Kamaitachi kamaitachi); 
	
	int  kamaitachiInsertCheck(Kamaitachi kamaitachi);
	
	
	//かまいたちの夜稼働データ全取得
	List<Kamaitachi> kamaitachiDateSelect();
	
	//日データ取得
	Kamaitachi kamaitachiSelectAll(int kamaitachi_id);
	
	//全データ取得(年月を絞る)
	List<KamaitachiYearMonth> kamaitachiYearMonthSelect();
	
	//月データ取得
	Kamaitachi kamaitachiMonthDataSelect(String year, String month);
	
	//全データ取得
	Kamaitachi kamaitachiAllDataSelect();
	
	//削除
	void kamaitachiDataDelete(int kamaitachi_id);
	
	
	
//===============まほいく=====================
	void mahoikuInsert(Mahoiku mahoiku);
	
	
	
}
