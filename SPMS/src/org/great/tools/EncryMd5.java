package org.great.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class EncryMd5 {
	private MessageDigest md5;
	private String newStr;

	public String encoderByMd5(String str) {
		try {
			//加密方法
			md5 = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			// 加密后的字符串
			newStr = base64en.encode(md5.digest(str.getBytes("utf-8")));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newStr;
	}



}
