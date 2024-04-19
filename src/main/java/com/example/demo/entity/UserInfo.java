package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー情報エンティティ
 */

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {

	/** ログインId */
	@Id
	@Column(name = "login_id")
	private String loginId;

	// パスワード
	private String password;

	// ログイン失敗回数
	@Column(name = "login_failure_count")
	private int loginFailureCount = 0;

	// アカウントロック日時
	@Column(name = "account_locked_time")
	private LocalDateTime accountLockedTime;

	// 利用可能か？
	@Column(name = "isDisabled")
	@Convert(converter = UserStatusConverter.class)
	private UserStatusKind status;

	// ユーザー権限
	@Convert(converter = UserAuthorityConverter.class)
	private AuthorityKind authority;

	// 登録日時
	@Column(name = "create_time")
	private LocalDateTime createTime;

	// 登録日時
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	// 最終更新ユーザー
	@Column(name = "update_user")
	private String updateUser;

	public UserInfo() {
	}

	/**
	 * ログイン失敗回数をインクリメント
	 * 
	 * @return　ログイン失敗回数がインクリメントされたUserInfo
	 */
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(
				loginId,
				password,
				++loginFailureCount,
				accountLockedTime,
				status,
				authority,
				createTime,
				updateTime,
				updateUser);
	}

	/**
	 * ログイン失敗情報をリセットする
	 * 
	 * @return ログイン失敗情報がリセットされた
	 */
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(
				loginId,
				password,
				0,
				null,
				status,
				authority,
				createTime,
				updateTime,
				updateUser);
	}

	public UserInfo updateAccountLocked() {
		return new UserInfo(
				loginId,
				password,
				0,
				LocalDateTime.now(),
				status,
				authority,
				createTime,
				updateTime,
				updateUser);
	}

}
