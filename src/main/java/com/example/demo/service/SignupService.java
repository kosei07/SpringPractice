package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報サービス
 */
@Service
@RequiredArgsConstructor
public class SignupService {
	private final UserInfoRepository repository;

	// Dozer Mapper
	private final Mapper mapper;

	// エンコーダー
	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザー情報テーブル　主キー検索
	 * @param loginId
	 * @return 登録情報: すでに同じloginIDが存在していればempty
	 */
	public Optional<UserInfo> resistUserInfo(SignupForm form) {
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if (userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}

		var userInfo = mapper.map(form, UserInfo.class);
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER.getAuthorityKind());

		return Optional.of(repository.save(userInfo));
	}
}
