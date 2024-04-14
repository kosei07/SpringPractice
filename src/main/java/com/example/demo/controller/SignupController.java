package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SignupMessage;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * サインアップコントローラー
 */
@Controller
@RequiredArgsConstructor
public class SignupController {
	/** サインアップ画面サービス */
	private final SignupService service;

	/** PasswordEncoder */

	//* メッセージソース */
	private final MessageSource messageSource;

	/**
	 * 
	 * @param model モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */

	@GetMapping("/signup")
	public String view(Model model, SignupForm form) {
		return "signup";
	}

	@PostMapping("/signup")
	public void signup(Model model, SignupForm form) {
		var userInfoOpt = service.resistUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
		var message = AppUtil.getMessage(messageSource, signupMessage.getMessageId());
		model.addAttribute("message", message);
		System.out.println(signupMessage.isError());
		model.addAttribute("isError", signupMessage.isError());

	}

	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCEED;
		}
	}
}
