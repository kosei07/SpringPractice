package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UniConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
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
	private final HttpSession session;

	// セッション情報

	/**
	 * 
	 * @param model モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */

	@GetMapping(UniConst.LOGIN)
	public String view(Model model, LoginForm form) {

		return "/login";
	}

	@GetMapping(value = UniConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());
		return "/login";
	}

	@PostMapping(UniConst.LOGIN)
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
