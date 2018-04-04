package com.zc.cris.shiro.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class testShiro {

	// 测试MD5盐值加密
	public static void main(String[] args) {
		
		String hashAlgorithmName = "SHA1";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 1024;

		Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(simpleHash);
	}

}
