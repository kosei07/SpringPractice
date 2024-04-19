package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserListInfo {
	private String loginId;

	private int loginFailureCount;

	private LocalDateTime accountLockedTime;

	private String status;

	private String authority;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private String updateUser;
}
