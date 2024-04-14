package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報サービス
 */
@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserInfoRepository repository;
	
	/**
	 * ユーザー情報テーブル　主キー検索
	 * @param loginId
	 * @return ユーザー情報
	 */
	public Optional<UserInfo>serchUserById(String loginId){
		return repository.findById(loginId);
	}
}
