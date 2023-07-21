package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KamaitachiRequest;
import com.example.demo.entity.Kamaitachi;
import com.example.demo.entity.KamaitachiProbability;
import com.example.demo.entity.KamaitachiYearMonth;
import com.example.demo.repository.UserMapper;

@Service
public class UserService {

	@Autowired UserMapper repository;

	//「かまいたちの夜」稼働データ登録
	public int kamaitachiInsert(KamaitachiRequest kamaitachiRequest) {
		int errorCount = 0;
		Kamaitachi kamaitachi = new Kamaitachi();
		//通常時
		String year = kamaitachiRequest.getDate().substring(0, 4);
		String month = kamaitachiRequest.getDate().substring(5, 7);
		String day = kamaitachiRequest.getDate().substring(8, 10);
		kamaitachi.setYear(year);
		kamaitachi.setMonth(month);
		kamaitachi.setDay(day);
		kamaitachi.setGameNum(kamaitachiRequest.getGameNum());
		kamaitachi.setSBIG(kamaitachiRequest.getSBIG());
		kamaitachi.setBIG(kamaitachiRequest.getBIG());
		kamaitachi.setREG(kamaitachiRequest.getREG());
		kamaitachi.setBeru(kamaitachiRequest.getBeru());
		kamaitachi.setSuika(kamaitachiRequest.getSuika());
		kamaitachi.setCherry(kamaitachiRequest.getCherry());
		kamaitachi.setIchimaiyakuA(kamaitachiRequest.getIchimaiyakuA());
		kamaitachi.setIchimaiyakuBC(kamaitachiRequest.getIchimaiyakuBC());
		kamaitachi.setEven(kamaitachiRequest.getEven());
		kamaitachi.setOdd(kamaitachiRequest.getOdd());
		kamaitachi.setSilhouette(kamaitachiRequest.getSilhouette());
		kamaitachi.setNomal(kamaitachiRequest.getNomal());
		//ビタ関連
		kamaitachi.setBitaSuccessNum(kamaitachiRequest.getBitaSuccessNum());
		kamaitachi.setBitaFailureNum(kamaitachiRequest.getBitaFailureNum());
		kamaitachi.setTenrakuBitaSuccessNum(kamaitachiRequest.getTenrakuBitaSuccessNum());
		kamaitachi.setTenrakuBitaFailureNum(kamaitachiRequest.getTenrakuBitaFailureNum());
		kamaitachi.setRtNum(kamaitachiRequest.getRtNum());




		System.out.println("xyz");


		errorCount = repository.kamaitachiInsertCheck(kamaitachi);
		System.out.println("xxx");



		if(errorCount==1) {
			return errorCount;
		}


		repository.kamaitachiInsert(kamaitachi);
		return errorCount;


	}


	//CSV登録
	public int csvchooser(String name) {
		int errorCount = 0;
		try {
			File file = new File("C:\\Users\\3030855\\Desktop\\かまいたちの夜\\"+name+".txt"); // 読み込むファイルのパスを指定
			if(file.exists()) {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {				 
					String line = scanner.nextLine();
					String[] s = line.split(",");
					if(s.length==18) {
						Kamaitachi kamaitachi = new Kamaitachi();
						String year =name.substring(0, 4);
						String month = name.substring(5, 7);
						String day = name.substring(8, 10);
						kamaitachi.setYear(year);
						kamaitachi.setMonth(month);
						kamaitachi.setDay(day);
						kamaitachi.setGameNum(Integer.parseInt(s[0]));
						kamaitachi.setSBIG(Integer.parseInt(s[1]));
						kamaitachi.setBIG(Integer.parseInt(s[2]));
						kamaitachi.setREG(Integer.parseInt(s[3]));
						kamaitachi.setBeru(Integer.parseInt(s[4]));
						kamaitachi.setSuika(Integer.parseInt(s[5]));
						kamaitachi.setCherry(Integer.parseInt(s[6]));
						kamaitachi.setIchimaiyakuA(Integer.parseInt(s[7]));
						kamaitachi.setIchimaiyakuBC(Integer.parseInt(s[8]));
						kamaitachi.setEven(Integer.parseInt(s[9]));
						kamaitachi.setOdd(Integer.parseInt(s[10]));
						kamaitachi.setSilhouette(Integer.parseInt(s[11]));
						kamaitachi.setNomal(Integer.parseInt(s[12]));
						//ビタ関連
						kamaitachi.setBitaSuccessNum(Integer.parseInt(s[13]));
						kamaitachi.setBitaFailureNum(Integer.parseInt(s[14]));
						kamaitachi.setTenrakuBitaSuccessNum(Integer.parseInt(s[15]));
						kamaitachi.setTenrakuBitaFailureNum(Integer.parseInt(s[16]));
						kamaitachi.setRtNum(Integer.parseInt(s[17]));


						System.out.println("abcde");
						repository.kamaitachiDateSelect();
						System.out.println("xxxxxxxxxx");

						errorCount = repository.kamaitachiInsertCheck(kamaitachi);



						if(errorCount==1) {
							return errorCount;
						}



						//DB登録
						repository.kamaitachiInsert(kamaitachi);


						//						System.out.println(file.getName());


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



	//全データ取得
	public Kamaitachi kamaitachiAllDataSelect() {
		return repository.kamaitachiAllDataSelect();
	}


	//登録日すべてを取得
	public List<Kamaitachi> kamaitachiDateSelect(){
		return repository.kamaitachiDateSelect();

	}


	//年月を取得
	public List<String> kamaitachiYearMonthSelect(){
		List<String> list = new ArrayList<>();
		for(KamaitachiYearMonth s : repository.kamaitachiYearMonthSelect()) {
			list.add(s.getYear()+"-"+s.getMonth());
		}
		return list;
	}


	//月データを取得
	public Kamaitachi kamaitachiMonthDataSelect(String yearMonth) {
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		return repository.kamaitachiMonthDataSelect(year, month);
	}



	//指定された日のデータを取得
	public Kamaitachi kamaitachiSelectAll(int kamaitachi_id){
		return repository.kamaitachiSelectAll(kamaitachi_id);

	}


	//削除
	public void kamaitachiDataDelete(int kamaitachi_id) {
		repository.kamaitachiDataDelete(kamaitachi_id);
	}


	//指定された月のデータの確率を取得
	public KamaitachiProbability kamaitachiSelectProbability(String yearMonth){
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		Kamaitachi k = repository.kamaitachiMonthDataSelect(year, month);
		KamaitachiProbability kp = new KamaitachiProbability();
		//通常時
		if(k.getSBIG()!=0) {
			kp.setSBIG(  (double)Math.round(((double)k.getGameNum()/k.getSBIG())*10)/10  );
		} else {
			kp.setSBIG(0);
		}
		if(k.getBIG()!=0) {
			kp.setBIG(  (double)Math.round(((double)k.getGameNum()/k.getBIG())*10)/10  );
		} else {
			kp.setBIG(0);
		}
		if(k.getREG()!=0) {
			kp.setREG(  (double)Math.round(((double)k.getGameNum()/k.getREG())*10)/10  );
		} else {
			kp.setREG(0);
		}
		if(k.getBeru()!=0) {
			kp.setBeru(  (double)Math.round(((double)k.getGameNum()/k.getBeru())*100)/100  );
		} else {
			kp.setBeru(0);
		}
		if(k.getSuika()!=0) {
			kp.setSuika(  (double)Math.round(((double)k.getGameNum()/k.getSuika())*10)/10  );
		} else {
			kp.setSuika(0);
		}
		if(k.getCherry()!=0) {
			kp.setCherry(  (double)Math.round(((double)k.getGameNum()/k.getCherry())*10)/10  );
		} else {
			kp.setCherry(0);
		}
		if(k.getIchimaiyakuA()!=0) {
			kp.setIchimaiyakuA(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuA())*10)/10  );
		} else {
			kp.setIchimaiyakuA(0);
		}
		if(k.getIchimaiyakuBC()!=0) {
			kp.setIchimaiyakuBC(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuBC())*10)/10  );
		} else {
			kp.setIchimaiyakuBC(0);
		}
		if(k.getEven()!=0 || k.getOdd()!=0) {
			kp.setEven(  (double)Math.round((((double)k.getEven()/(k.getEven() + k.getOdd() ))*1000)) /10 );
			kp.setOdd(  (double)Math.round((((double)k.getOdd()/(k.getEven() + k.getOdd() ))*1000)) /10 ) ;		
		} else {
			kp.setEven(0);
			kp.setOdd(0);
		}
		if(k.getSilhouette()!=0 || k.getNomal()!=0) {
			kp.setSilhouette(  (double)Math.round((((double)k.getSilhouette()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
			kp.setNomal(  (double)Math.round((((double)k.getNomal()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
		} else {
			kp.setSilhouette(0);
			kp.setNomal(0);
		}
		//ビタ関連
		if((k.getBitaSuccessNum()+k.getBitaFailureNum())!=0) {
			kp.setBitaSuccess( (double)Math.round((((double)k.getBitaSuccessNum()/(k.getBitaSuccessNum() + k.getBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setBitaSuccess(0);
		}
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum())!=0) {
			kp.setTenrakuBitaSuccess(  (double)Math.round((((double)k.getTenrakuBitaSuccessNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setTenrakuBitaSuccess(0);
		}
		double totalBita = (double)k.getBitaSuccessNum() + (double)k.getBitaFailureNum() + (double)k.getTenrakuBitaSuccessNum() + (double)k.getTenrakuBitaFailureNum() ;
		if(totalBita!=0) {
			kp.setTotalBitaSuccess( (double)Math.round(( (( (double)k.getTenrakuBitaSuccessNum() + (double)k.getBitaSuccessNum() ) / totalBita )*1000))/10  );

		} else {
			kp.setTotalBitaSuccess(0);
		}
		//RT突入率
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ) !=0) {
			kp.setRtNum(  (double)Math.round((((double)k.getRtNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ))*1000))/10  );	
		} else {
			kp.setRtNum(0);
		}
		return kp;
	}


	//指定された日のデータの確率を取得
	public KamaitachiProbability kamaitachiSelectProbability(int kamaitachi_id){
		Kamaitachi k = repository.kamaitachiSelectAll(kamaitachi_id);
		KamaitachiProbability kp = new KamaitachiProbability();
		//通常時
		if(k.getSBIG()!=0) {
			kp.setSBIG(  (double)Math.round(((double)k.getGameNum()/k.getSBIG())*10)/10  );
		} else {
			kp.setSBIG(0);
		}
		if(k.getBIG()!=0) {
			kp.setBIG(  (double)Math.round(((double)k.getGameNum()/k.getBIG())*10)/10  );
		} else {
			kp.setBIG(0);
		}
		if(k.getREG()!=0) {
			kp.setREG(  (double)Math.round(((double)k.getGameNum()/k.getREG())*10)/10  );
		} else {
			kp.setREG(0);
		}
		if(k.getBeru()!=0) {
			kp.setBeru(  (double)Math.round(((double)k.getGameNum()/k.getBeru())*100)/100  );
		} else {
			kp.setBeru(0);
		}
		if(k.getSuika()!=0) {
			kp.setSuika(  (double)Math.round(((double)k.getGameNum()/k.getSuika())*10)/10  );
		} else {
			kp.setSuika(0);
		}
		if(k.getCherry()!=0) {
			kp.setCherry(  (double)Math.round(((double)k.getGameNum()/k.getCherry())*10)/10  );
		} else {
			kp.setCherry(0);
		}
		if(k.getIchimaiyakuA()!=0) {
			kp.setIchimaiyakuA(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuA())*10)/10  );
		} else {
			kp.setIchimaiyakuA(0);
		}
		if(k.getIchimaiyakuBC()!=0) {
			kp.setIchimaiyakuBC(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuBC())*10)/10  );
		} else {
			kp.setIchimaiyakuBC(0);
		}
		if(k.getEven()!=0 || k.getOdd()!=0) {
			kp.setEven(  (double)Math.round((((double)k.getEven()/(k.getEven() + k.getOdd() ))*1000)) /10 );
			kp.setOdd(  (double)Math.round((((double)k.getOdd()/(k.getEven() + k.getOdd() ))*1000)) /10 ) ;		
		} else {
			kp.setEven(0);
			kp.setOdd(0);
		}
		if(k.getSilhouette()!=0 || k.getNomal()!=0) {
			kp.setSilhouette(  (double)Math.round((((double)k.getSilhouette()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
			kp.setNomal(  (double)Math.round((((double)k.getNomal()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
		} else {
			kp.setSilhouette(0);
			kp.setNomal(0);
		}
		//ビタ関連
		if((k.getBitaSuccessNum()+k.getBitaFailureNum())!=0) {
			kp.setBitaSuccess( (double)Math.round((((double)k.getBitaSuccessNum()/(k.getBitaSuccessNum() + k.getBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setBitaSuccess(0);
		}
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum())!=0) {
			kp.setTenrakuBitaSuccess(  (double)Math.round((((double)k.getTenrakuBitaSuccessNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setTenrakuBitaSuccess(0);
		}
		double totalBita = (double)k.getBitaSuccessNum() + (double)k.getBitaFailureNum() + (double)k.getTenrakuBitaSuccessNum() + (double)k.getTenrakuBitaFailureNum() ;
		if(totalBita!=0) {
			kp.setTotalBitaSuccess( (double)Math.round(( (( (double)k.getTenrakuBitaSuccessNum() + (double)k.getBitaSuccessNum() ) / totalBita )*1000))/10  );

		} else {
			kp.setTotalBitaSuccess(0);
		}
		//RT突入率
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ) !=0) {
			kp.setRtNum(  (double)Math.round((((double)k.getRtNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ))*1000))/10  );	
		} else {
			kp.setRtNum(0);
		}
		return kp;
	}



	//全データの確率を取得
	public KamaitachiProbability kamaitachiAllSelectProbability(){
		Kamaitachi k = repository.kamaitachiAllDataSelect();
		KamaitachiProbability kp = new KamaitachiProbability();
		//通常時
		if(k.getSBIG()!=0) {
			kp.setSBIG(  (double)Math.round(((double)k.getGameNum()/k.getSBIG())*10)/10  );
		} else {
			kp.setSBIG(0);
		}
		if(k.getBIG()!=0) {
			kp.setBIG(  (double)Math.round(((double)k.getGameNum()/k.getBIG())*10)/10  );
		} else {
			kp.setBIG(0);
		}
		if(k.getREG()!=0) {
			kp.setREG(  (double)Math.round(((double)k.getGameNum()/k.getREG())*10)/10  );
		} else {
			kp.setREG(0);
		}
		if(k.getBeru()!=0) {
			kp.setBeru(  (double)Math.round(((double)k.getGameNum()/k.getBeru())*100)/100  );
		} else {
			kp.setBeru(0);
		}
		if(k.getSuika()!=0) {
			kp.setSuika(  (double)Math.round(((double)k.getGameNum()/k.getSuika())*10)/10  );
		} else {
			kp.setSuika(0);
		}
		if(k.getCherry()!=0) {
			kp.setCherry(  (double)Math.round(((double)k.getGameNum()/k.getCherry())*10)/10  );
		} else {
			kp.setCherry(0);
		}
		if(k.getIchimaiyakuA()!=0) {
			kp.setIchimaiyakuA(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuA())*10)/10  );
		} else {
			kp.setIchimaiyakuA(0);
		}
		if(k.getIchimaiyakuBC()!=0) {
			kp.setIchimaiyakuBC(  (double)Math.round(((double)k.getGameNum()/k.getIchimaiyakuBC())*10)/10  );
		} else {
			kp.setIchimaiyakuBC(0);
		}
		if(k.getEven()!=0 || k.getOdd()!=0) {
			kp.setEven(  (double)Math.round((((double)k.getEven()/(k.getEven() + k.getOdd() ))*1000)) /10 );
			kp.setOdd(  (double)Math.round((((double)k.getOdd()/(k.getEven() + k.getOdd() ))*1000)) /10 ) ;		
		} else {
			kp.setEven(0);
			kp.setOdd(0);
		}
		if(k.getSilhouette()!=0 || k.getNomal()!=0) {
			kp.setSilhouette(  (double)Math.round((((double)k.getSilhouette()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
			kp.setNomal(  (double)Math.round((((double)k.getNomal()/(k.getSilhouette() + k.getNomal() ))*1000)) /10);
		} else {
			kp.setSilhouette(0);
			kp.setNomal(0);
		}
		//ビタ関連
		if((k.getBitaSuccessNum()+k.getBitaFailureNum())!=0) {
			kp.setBitaSuccess( (double)Math.round((((double)k.getBitaSuccessNum()/(k.getBitaSuccessNum() + k.getBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setBitaSuccess(0);
		}
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum())!=0) {
			kp.setTenrakuBitaSuccess(  (double)Math.round((((double)k.getTenrakuBitaSuccessNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() ))*1000))/10  );
		} else {
			kp.setTenrakuBitaSuccess(0);
		}
		double totalBita = (double)k.getBitaSuccessNum() + (double)k.getBitaFailureNum() + (double)k.getTenrakuBitaSuccessNum() + (double)k.getTenrakuBitaFailureNum() ;
		if(totalBita!=0) {
			kp.setTotalBitaSuccess( (double)Math.round(( (( (double)k.getTenrakuBitaSuccessNum() + (double)k.getBitaSuccessNum() ) / totalBita )*1000))/10  );

		} else {
			kp.setTotalBitaSuccess(0);
		}
		//RT突入率
		if((k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ) !=0) {
			kp.setRtNum(  (double)Math.round((((double)k.getRtNum()/(k.getTenrakuBitaSuccessNum() + k.getTenrakuBitaFailureNum() + k.getRtNum() ))*1000))/10  );	
		} else {
			kp.setRtNum(0);
		}
		return kp;
	}



	/*CSV拡張子を取る*/
	public String csvExtensionRemove(String fileName) {

		if(fileName.contains(".txt")) {
			fileName = fileName.substring(0, fileName.length()-4);			
		}
		System.out.println(fileName);
		return fileName;
	}





}
