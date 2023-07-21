package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MahoikuRequest;
import com.example.demo.entity.Mahoiku;
import com.example.demo.entity.MahoikuProbability;
import com.example.demo.repository.MahoikuMapper;

@Service
public class MahoikuService {
	@Autowired MahoikuMapper repository;

	public int mahoikuInsert(MahoikuRequest mahoikuRequest) {
		int errorCount = 0;
		Mahoiku mahoiku = new Mahoiku();

		//通常時
		String year = mahoikuRequest.getDate().substring(0, 4);
		String month = mahoikuRequest.getDate().substring(5, 7);
		String day = mahoikuRequest.getDate().substring(8, 10);
		mahoiku.setYear(year);
		mahoiku.setMonth(month);
		mahoiku.setDay(day);
		mahoiku.setFrame(mahoikuRequest.getFrame());
		mahoiku.setTotalGameNum(mahoikuRequest.getTotalGameNum());
		mahoiku.setGameNum(mahoikuRequest.getGameNum());
		mahoiku.setArtGameNum(mahoikuRequest.getArtGameNum());
		mahoiku.setSBIG(mahoikuRequest.getSBIG());
		mahoiku.setBIG(mahoikuRequest.getBIG());
		mahoiku.setREG(mahoikuRequest.getREG());
		mahoiku.setTrial(mahoikuRequest.getTrial());
		mahoiku.setTrialSuccess(mahoikuRequest.getTrialSuccess());
		mahoiku.setEpisode(mahoikuRequest.getEpisode());
		mahoiku.setBattle(mahoikuRequest.getBattle());
		mahoiku.setSuperBattle(mahoikuRequest.getSuperBattle());
		mahoiku.setSurvival(mahoikuRequest.getSurvival());
		mahoiku.setRaging(mahoikuRequest.getRaging());


		mahoiku.setBita(mahoikuRequest.getBita());
		mahoiku.setBitaSuccess(mahoikuRequest.getBitaSuccess());

		mahoiku.setRelief(mahoikuRequest.getRelief());
		mahoiku.setBigBita(mahoikuRequest.getBigBita());
		mahoiku.setBigBitaSuccessNum(mahoikuRequest.getBigBitaSuccess());
		mahoiku.setBigRelief(mahoikuRequest.getBigRelief());

		errorCount = repository.mahoikuInsertCheck(mahoiku);

		if(errorCount==1) {
			return errorCount;
		}
		repository.mahoikuInsert(mahoiku);
		return errorCount;
	}




	//CSV登録
	public int csvchooser(String name) {
		int errorCount = 0;
		try {
			File file = new File("C:\\Users\\3030743\\Desktop\\まほいく\\"+name+".txt"); // 読み込むファイルのパスを指定
			if(file.exists()) {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {				 
					String line = scanner.nextLine();
					String[] s = line.split(",");
					if(s.length==20) {
						Mahoiku mahoiku = new Mahoiku();

						String year =name.substring(0, 4);
						String month = name.substring(5, 7);
						String day = name.substring(8, 10);
						mahoiku.setYear(year);
						mahoiku.setMonth(month);
						mahoiku.setDay(day);


						mahoiku.setFrame(s[0]);
						mahoiku.setTotalGameNum(Integer.parseInt(s[1]));
						mahoiku.setGameNum(Integer.parseInt(s[2]));
						mahoiku.setArtGameNum(Integer.parseInt(s[3]));
						mahoiku.setSBIG(Integer.parseInt(s[4]));
						mahoiku.setBIG(Integer.parseInt(s[5]));
						mahoiku.setREG(Integer.parseInt(s[6]));
						mahoiku.setTrial(Integer.parseInt(s[7]));
						mahoiku.setTrialSuccess(Integer.parseInt(s[8]));
						mahoiku.setEpisode(Integer.parseInt(s[9]));
						mahoiku.setBattle(Integer.parseInt(s[10]));
						mahoiku.setSuperBattle(Integer.parseInt(s[11]));
						mahoiku.setSurvival(Integer.parseInt(s[12]));
						mahoiku.setRaging(Integer.parseInt(s[13]));


						mahoiku.setBita(Integer.parseInt(s[14]));
						mahoiku.setBitaSuccess(Integer.parseInt(s[15]));

						mahoiku.setRelief(Integer.parseInt(s[16]));
						mahoiku.setBigBita(Integer.parseInt(s[17]));
						mahoiku.setBigBitaSuccessNum(Integer.parseInt(s[18]));
						mahoiku.setBigRelief(Integer.parseInt(s[19]));

						errorCount = repository.mahoikuInsertCheck(mahoiku);
						if(errorCount==1) {
							return errorCount;
						}
						//DB登録
						repository.mahoikuInsert(mahoiku);
					} else {
						errorCount=2;
						break;
					}
				}
				scanner.close();
			} else {
				errorCount=2;
				return errorCount;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return errorCount;
	}


	//登録日すべてを取得
	public List<Mahoiku> mahoikuDateSelect(){
		return repository.mahoikuDateSelect();

	}


	//指定された日のデータを取得
	public Mahoiku mahoikuSelect(int mahoiku_id){
		return repository.mahoikuSelect(mahoiku_id);

	}



	//指定された日のデータの確率を取得
	public MahoikuProbability mahoikuSelectProbability(int mahoiku_id){
		Mahoiku m = repository.mahoikuSelect(mahoiku_id);
		MahoikuProbability mp = new MahoikuProbability();
		//通常時
		if(m.getSBIG()!=0) {
			mp.setSBIG(  (double)Math.round(((double)m.getTotalGameNum()/m.getSBIG())*10)/10  );
		} else {
			mp.setSBIG(0);
		}
		if(m.getBIG()!=0) {
			mp.setBIG(  (double)Math.round(((double)m.getTotalGameNum()/m.getBIG())*10)/10  );
		} else {
			mp.setBIG(0);
		}
		if(m.getREG()!=0) {
			mp.setREG(  (double)Math.round(((double)m.getTotalGameNum()/m.getREG())*10)/10  );
		} else {
			mp.setREG(0);
		}
		if(m.getSBIG()!=0 || m.getBIG()!=0) {
			mp.setBigProbability(  (double)Math.round(((double)m.getTotalGameNum()/(m.getSBIG() + m.getBIG()))*10)/10  );
		} else {
			mp.setBigProbability(0);
		}
		if(m.getBIG()!=0 ||  m.getBIG()!=0 || m.getREG()!=0) {
			mp.setBonusProbability(  (double)Math.round(((double)m.getTotalGameNum()/(m.getSBIG() + m.getBIG() + m.getREG()))*10)/10  );
		} else {
			mp.setBonusProbability(0);
		}

		//まほいくとらいある

		if(m.getTrial()!=0) {
			mp.setTrial(  (double)Math.round(((double)m.getTotalGameNum()/m.getTrial())*10)/10  );
		} else {
			mp.setTrial(0);
		}			
		if(m.getTrial()!=0) {
			mp.setTrialSuccess(  (double)Math.round(((double)m.getTrialSuccess()/m.getTrial())*100)  );
		} else {
			mp.setTrialSuccess(0);
		}
		if(m.getEpisode()!=0) {
			mp.setEpisode(  (double)Math.round(((double)m.getTotalGameNum()/m.getEpisode())*10)/10  );
		} else {
			mp.setEpisode(0);
		}
		if(m.getBattle()!=0) {
			mp.setBattle(  (double)Math.round(((double)m.getTotalGameNum()/m.getBattle())*10)/10  );
		} else {
			mp.setBattle(0);
		}
		if(m.getSuperBattle()!=0) {
			mp.setSuperBattle(  (double)Math.round(((double)m.getTotalGameNum()/m.getSuperBattle())*10)/10  );
		} else {
			mp.setSuperBattle(0);
		}
		if(m.getSurvival()!=0) {
			mp.setSurvival(  (double)Math.round(((double)m.getTotalGameNum()/m.getSurvival())*10)/10  );
		} else {
			mp.setSurvival(0);
		}
		if(m.getRaging()!=0) {
			mp.setRaging(  (double)Math.round((((double)m.getTotalGameNum()/m.getRaging())*1000)) /10 );
		} else {
			mp.setRaging(0);
		}


		if(m.getBita()!=0) {
			mp.setBitaSuccess(  (double)Math.round((((double)m.getBitaSuccess()/m.getBita())*1000)) /10);
		} else {
			mp.setBitaSuccess(0);
		}
		if(m.getRelief()!=0) {
			mp.setRelief( (double)Math.round((((double)m.getRelief()/(m.getBita() - m.getBitaSuccess() ))*1000))/10  );
		} else {
			mp.setRelief(0);
		}

		if(m.getBigBitaSuccessNum()!=0) {
			mp.setBigBitaSuccess(  (double)Math.round((((double)m.getBigBitaSuccessNum()/m.getBigBita())*1000)) /10);
		} else {
			mp.setBigBitaSuccess(0);
		}
		if(m.getBigRelief()!=0) {
			mp.setBigRelief( (double)Math.round((((double)m.getBigRelief()/(m.getBigBita() - m.getBigBitaSuccessNum() ))*1000))/10  );
		} else {
			mp.setBigRelief(0);
		}

		if(m.getBita()!=0 || m.getBigRelief()!=0) {
			mp.setTotalBitaSuccess( (double)Math.round(((((double)m.getBitaSuccess() + (double)m.getBigBitaSuccessNum())/(m.getBigBita() + m.getBita() ))*1000))/10  );
		} else {
			mp.setTotalBitaSuccess(0);
		}


		return mp;
	}




	//削除
	public void mahoikuDataDelete(int mahoiku_id) {
		repository.mahoikuDataDelete(mahoiku_id);
	}


	//年月を取得
	public List<String> mahoikuYearMonthSelect(){
		List<String> list = new ArrayList<>();
		for(Mahoiku s : repository.mahoikuYearMonthSelect()) {
			list.add(s.getYear()+"-"+s.getMonth());
		}
		return list;
	}



	//月データを取得
	public Mahoiku mahoikuMonthDataSelect(String yearMonth) {
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		return repository.mahoikuMonthDataSelect(year, month);
	}

	
	//月データ(枠色：赤)カウント取得
		public int akaikuMonthCount(String yearMonth) {
			String year = yearMonth.substring(0, 4);
			String month = yearMonth.substring(5, 7);
			return repository.akaikuMonthCount(year, month);
		}
		
		

		//月データ(枠色：青)カウント取得
			public int aoikuMonthCount(String yearMonth) {
				String year = yearMonth.substring(0, 4);
				String month = yearMonth.substring(5, 7);
				return repository.aoikuMonthCount(year, month);
			}
			
		
		
		
	
	//月データ(枠色：赤)を取得
	public Mahoiku akaikuMonthDataSelect(String yearMonth) {
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		return repository.akaikuMonthDataSelect(year, month);
	}
	
	//月データ(枠色：青)を取得
		public Mahoiku aoikuMonthDataSelect(String yearMonth) {
			String year = yearMonth.substring(0, 4);
			String month = yearMonth.substring(5, 7);
			return repository.aoikuMonthDataSelect(year, month);
		}
	
	

}
