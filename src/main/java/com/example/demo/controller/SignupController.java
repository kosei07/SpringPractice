package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignupMessage;
import com.example.demo.constant.UniConst;
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
	 * @param bdResult　入力チェック結果
	 * @return　表示画面
	 */

	@GetMapping(UniConst.SIGNUP)
	public String view(Model model, SignupForm form) {
		return "signup";
	}

	@PostMapping(UniConst.SIGNUP)
	public void signup(Model model, @Validated SignupForm form, BindingResult bdResult) {

		if (bdResult.hasErrors()) {
			editGuidMessage(model, MessageConst.FORM_ERROR, true);
			return;
		}

		var userInfoOpt = service.resistUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
		editGuidMessage(model, signupMessage.getMessageId(), signupMessage.isError());

	}

	private void editGuidMessage(Model model, String messageId, boolean isError) {
		var message = AppUtil.getMessage(messageSource, messageId);
		model.addAttribute("message", message);
		model.addAttribute("isError", isError);
	}

	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCEED;
		}
	}
}
