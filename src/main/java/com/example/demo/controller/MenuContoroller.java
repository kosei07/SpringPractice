package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;

/**
 * メニューコントローラー
 */

@Controller
public class MenuContoroller {
	/**
	 * 
	 * @return　表示画面
	 */
	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model) {
		var hasUserManageAuth = user.getAuthorities()
				.stream()
				.allMatch(authority -> {
					System.out.println(authority.getAuthority());
					System.out.println(AuthorityKind.ITEM_AND_USER_MANAGER.getCode());

					return authority.getAuthority()
							.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode());
				});

		model.addAttribute("hasUserManageAuth", hasUserManageAuth);

		return "menu";
	}
}
