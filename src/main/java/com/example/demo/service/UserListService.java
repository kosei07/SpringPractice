package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserListInfo;
import com.example.demo.form.UserListForm;

public interface UserListService {
	public List<UserListInfo> editUserList();

	public List<UserListInfo> editUserListByParam(UserListForm form);
}
