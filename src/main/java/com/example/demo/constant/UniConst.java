package com.example.demo.constant;

// 定数クラス
public class UniConst {

	public static final String LOGIN = "/login";

	public static final String SIGNUP = "/signup";

	public static final String MENU = "/menu";

	// 認証不要画面
	public static final String[] NO_AUTHENTICATION = { SIGNUP, LOGIN, "/webjars/**" };
}
