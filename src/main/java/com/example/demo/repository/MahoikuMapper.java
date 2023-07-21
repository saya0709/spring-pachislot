package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Mahoiku;

@Mapper
public interface MahoikuMapper {
	
	void mahoikuInsert(Mahoiku mahoiku);
	
	int mahoikuInsertCheck(Mahoiku mahoiku);
	
	//稼働データ全取得
	List<Mahoiku> mahoikuDateSelect();
	
	Mahoiku mahoikuSelect(int mahoiku_id);
	
	
	//削除
	void mahoikuDataDelete(int mahoiku_id);
	
	
	//全データ取得(年月を絞る)
	List<Mahoiku> mahoikuYearMonthSelect();
	
	
	//月データ取得
	Mahoiku mahoikuMonthDataSelect(String year, String month);
	
	//月データ(枠色：赤)カウント取得
	int akaikuMonthCount(String year, String month);
	
	//月データ(枠色：青)カウント取得
	int aoikuMonthCount(String year, String month);
		
	
	//月データ(枠色：赤)取得
	Mahoiku akaikuMonthDataSelect(String year, String month);
		
	//月データ(枠色：青)取得
	Mahoiku aoikuMonthDataSelect(String year, String month);
			
		
	
}

