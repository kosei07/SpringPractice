package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニューコントローラー
 */

@Controller
public class MenuContoroller {
	/**
	 * 
	 * @return　表示画面
	 */
	@GetMapping("/menu")
	public String view() {
		return "menu";
	}
}
