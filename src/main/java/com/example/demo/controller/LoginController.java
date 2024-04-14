package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログインコントローラー
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
	/** ログイン画面サービス */
	private final LoginService service;

	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;

	//* メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 
	 * @param model モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */

	@GetMapping("login")
	public String view(Model model, LoginForm form) {

		return "login";
	}

	@PostMapping("login")
	public String login(Model model, LoginForm form) {
		var userInfo = service.serchUserById(form.getLoginId());
		var isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			var errorMessage = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMessage);
			return "login";
		}
	}
}
