package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class UserListForm {

	@Length(min = 8, max = 20)
	private String loginId;

	private UserStatusKind userStatusKind;

	private AuthorityKind authorityKind;
}
