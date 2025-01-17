package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/** 
 * アプリケーション共通クラス
 * 
 * @author inouekousei
 * 
 */
public class AppUtil {
	public static String getMessage(MessageSource messageSource, String key, Object... params) {
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}

	public static String addWildcard(String param) {
		return "%" + param + "%";
	}
}
