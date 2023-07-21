package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.KamaitachiDay;
import com.example.demo.dto.KamaitachiMonth;
import com.example.demo.dto.KamaitachiRequest;
import com.example.demo.dto.kamaitachiCSV;
import com.example.demo.entity.Kamaitachi;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	private String month;
	private String day;
	private String deleteDay;
	private String fileName;
	@Autowired
	private UserService userService;




	@GetMapping("/")
	public String home() {
		return "top";
	}
	@GetMapping("/top")
	public String top() {
		return "top";
	}
	@GetMapping("/qa")
	public String qa() {
		return "qa";
	}



	@GetMapping("/kamaitachi")
	public String kamaitachi() {
		return "kamaitachi";
	}





	@GetMapping("/mahoiku")
	public String mahoiku() {
		return "mahoiku";
	}









	/*===========かまいたちの夜==================*/
	/*データ登録*/
	@GetMapping("/kamaitachi/dataInsert")
	public String kamaitachiDataInsert(Model model) {
		model.addAttribute("kamaitachiRequest", new KamaitachiRequest());
		return "kamaitachi/add";
	}

	@PostMapping("/kamaitachi/dataInsert")
	public String kamaitachiAdd(@Validated @ModelAttribute KamaitachiRequest userRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "kamaitachi/add";
		}
		// ユーザー情報の登録
		userService.kamaitachiInsert(userRequest);
		model.addAttribute("kamaitachiRequest", new KamaitachiRequest());
		return "redirect:/kamaitachi";
	}












	@GetMapping("/kamaitachi/csv")
	public String kamaitachiDataInsertCsv(Model model) {
		model.addAttribute("kamaitachiCsv", new kamaitachiCSV());
		model.addAttribute("fileNmae", fileName);

		return "kamaitachi/csv";
	}

	@PostMapping("/kamaitachi/csvAdd")
	public String kamaitachiCsvAdd(@RequestParam("file") MultipartFile file, @Validated @ModelAttribute kamaitachiCSV kamaitachiCsv, BindingResult result ,Model model) {
		// 入力チェックエラーの場合
		if (!file.isEmpty()) {
			// ファイルを保存したり、別の処理を行ったりする場合はここに記述する
		}
		fileName = userService.csvExtensionRemove(file.getOriginalFilename());
		
		// ユーザー情報の登録
		int errorcount = userService.csvchooser(fileName);
		System.out.println("errorcount+"+errorcount);
		List<String> errorList = new ArrayList<String>();
		switch(errorcount) {
		case 1:
			errorList.add("すでに登録済みです");
			model.addAttribute("errorMessage", "同じデータが登録されています。");
			System.out.println("abc");
			model.addAttribute("kamaitachiCsv", new kamaitachiCSV());
			return "kamaitachi/csv";
		case 2:
			errorList.add("ファイルが存在しません");
			model.addAttribute("errorMessage", "ファイルが存在しません");
			model.addAttribute("kamaitachiCsv", new kamaitachiCSV());
			return "kamaitachi/csv";
		}
		model.addAttribute("kamaitachiRequest", new KamaitachiRequest());

		return "redirect:/kamaitachi";
	}
	
	
	
	
	
	@PostMapping("/kamaitachi/csvChooser")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("csvChooser");
		//		fileName = file.getOriginalFilename();
		//		System.out.println(fileName);		
		//		model.addAttribute("kamaitachiCsv", new kamaitachiCSV());
		//		model.addAttribute("fileNmae", fileName);
		//		
		if (!file.isEmpty()) {
			// ファイルを保存したり、別の処理を行ったりする場合はここに記述する
		}
		String fileName = userService.csvExtensionRemove(file.getOriginalFilename());
		model.addAttribute("fileName", fileName);
		return "kamaitachi/csv"; // アップロード成功後に表示するページへリダイレクトします

		//		return "redirect:/kamaitachi/csv"; // アップロード成功後に表示するページへリダイレクトします
	}
	/*ここまでデータ登録*/	




	/*データ表示選択*/
	@GetMapping("/kamaitachi/dataDisplay")
	public String kamaitachiView(@Validated @ModelAttribute KamaitachiMonth kamaitachi, BindingResult result, Model model) {
		List<Kamaitachi> list ;
		List<String> list2;
		list = userService.kamaitachiDateSelect();
		list2 = userService.kamaitachiYearMonthSelect();
		model.addAttribute("kamaitachiDate", list);
		model.addAttribute("kamaitachiYearMonthSelect", list2);
		return "kamaitachi/display";
	}

	/*日データ表示受付*/
	@PostMapping("/kamaitachi/dayError")
	public String kamaitachiShow(@RequestParam("selectedId") String inputValue, @Validated @ModelAttribute KamaitachiDay kamaitachi, BindingResult result, Model model) {
		System.out.println(inputValue);
		day = inputValue;
		// 入力チェックエラーの場合
		if (day.equals("00")) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			List<Kamaitachi> list ;
			List<String> list2;
			list = userService.kamaitachiDateSelect();
			list2 = userService.kamaitachiYearMonthSelect();
			model.addAttribute("dayValidation", errorList);
			model.addAttribute("kamaitachiDate", list);
			model.addAttribute("kamaitachiYearMonthSelect", list2);
			return "kamaitachi/display";
		} else {
			return "redirect:/kamaitachi/DayData";
		}
	}

	/*日データ表示ページ*/
	@GetMapping("/kamaitachi/DayData")
	public String kamaitachiDayView(Model model) {
		Kamaitachi kama = userService.kamaitachiSelectAll(Integer.parseInt( day ));
		model.addAttribute("kamaitachiSelectAll", kama);
		return "kamaitachi/view";
	}


	/*日データ削除受付*/
	@PostMapping("/kamaitachi/deleteError")
	public String kamaitachiDelete(@RequestParam("selectedId") String inputValue, @Validated @ModelAttribute KamaitachiDay kamaitachi, BindingResult result, Model model) {
		System.out.println(inputValue);
		deleteDay = inputValue;
		// 入力チェックエラーの場合
		if (deleteDay.equals("00")) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			List<Kamaitachi> list ;
			List<String> list2;
			list = userService.kamaitachiDateSelect();
			list2 = userService.kamaitachiYearMonthSelect();
			model.addAttribute("daleteDayValidation", errorList);
			model.addAttribute("kamaitachiDate", list);
			model.addAttribute("kamaitachiYearMonthSelect", list2);
			return "kamaitachi/display";
		} else {
			userService.kamaitachiDataDelete(Integer.parseInt(deleteDay));
			return "redirect:/kamaitachi/dataDisplay";
		}
	}



	/*月データ表示受付*/
	@PostMapping("/kamaitachi/monthError")
	public String kamaitachiYearMonthShow(@RequestParam("selectedId") String yearMonth, @Validated @ModelAttribute KamaitachiMonth kamaitachi, BindingResult result, Model model) {
		month = yearMonth;
		// 入力チェックエラーの場合
		if (month.equals("00")) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			List<Kamaitachi> list ;
			List<String> list2;
			list = userService.kamaitachiDateSelect();
			list2 = userService.kamaitachiYearMonthSelect();
			model.addAttribute("monthValidation", errorList);
			model.addAttribute("kamaitachiDate", list);
			model.addAttribute("kamaitachiYearMonthSelect", list2);
			return "kamaitachi/display";
		} else {
			return "redirect:/kamaitachi/MonthData";
		}
	}

	/*月データ表示ページ*/
	@GetMapping("/kamaitachi/MonthData")
	public String kamaitachiMonthView(Model model) {
		Kamaitachi kama = userService.kamaitachiMonthDataSelect(month);
		model.addAttribute("kamaitachiMonthDate", kama);
		return "kamaitachi/monthview";
	}



	/*全データ表示ページ*/
	@GetMapping("/kamaitachi/allData")
	public String kamaitachiAllView(Model model) {
		Kamaitachi kama = userService.kamaitachiAllDataSelect();
		model.addAttribute("kamaitachiAllData", kama);
		return "kamaitachi/allview";
	}













}






