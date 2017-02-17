package com.maju.common.encode.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.maju.common.encode.MD5Pwd;

/**
 * md5密码加密
 * @author cl
 *
 */
public class MD5PwdImpl implements MD5Pwd{

	//加密
	@Override
	public String encode(String password) {
		String algorithm = "MD5";
		//加盐
		/*Random r=new Random();
		StringBuilder sb=new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len=sb.length();
		if(len<16){
			for(int i=0;i<16-len;i++){
				sb.append("0");
			}
		}
		String salt=sb.toString();
		password+=salt;*/
		MessageDigest instance = null;
		try {
			instance = MessageDigest.getInstance(algorithm );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//加密
		byte[] digest = instance.digest(password.getBytes());
		//十六进制加密
		char[] encodeHex = Hex.encodeHex(digest);
		return new String(encodeHex);
	}
	@Test
	public void test(){
		String encode = encode("123");
		System.out.println(encode);
		String encode2 = encode("123");
		System.out.println(encode2);
	}
}
