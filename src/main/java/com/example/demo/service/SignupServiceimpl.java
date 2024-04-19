package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報サービス
 */
@Service
@RequiredArgsConstructor
public class SignupServiceimpl implements SignupService {
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
	@Override
	public Optional<UserInfo> resistUserInfo(SignupForm form) {
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if (userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}

		var userInfo = mapper.map(form, UserInfo.class);
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		userInfo.setStatus(UserStatusKind.ENABLED);
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER);
		userInfo.setCreateTime(LocalDateTime.now());
		userInfo.setUpdateTime(LocalDateTime.now());
		userInfo.setUpdateUser(form.getLoginId());

		return Optional.of(repository.save(userInfo));
	}
}
