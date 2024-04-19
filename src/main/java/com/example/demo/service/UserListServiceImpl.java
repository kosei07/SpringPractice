package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserListInfo;
import com.example.demo.form.UserListForm;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {
	private final UserInfoRepository repository;

	private final Mapper mapper;

	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}

	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
		var userListInfos = new ArrayList<UserListInfo>();
		for (UserInfo userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, UserListInfo.class);
			userListInfo.setStatus(userInfo.getStatus().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthority().getDisplayValue());
			userListInfos.add(userListInfo);
		}

		return userListInfos;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UserListInfo> editUserListByParam(UserListForm form) {
		var userInfo = mapper.map(form, UserInfo.class);
		return toUserListInfos(findUserInfoByParam(userInfo));
	}

	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	@Override
	private List<UserInfo> findUserInfoByParam(UserInfo userInfo) {
		var loginIdParam = AppUtil.addWildcard(userInfo.getLoginId());

		if (userInfo.getStatus() != null && userInfo.getAuthority() != null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
					userInfo.getStatus(), userInfo.getAuthority());
		} else if (userInfo.getStatus() != null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, userInfo.getStatus());
		} else if (userInfo.getAuthority() != null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, userInfo.getAuthority());
		} else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

}
