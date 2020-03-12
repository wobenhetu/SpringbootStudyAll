package com.springboot.commonutils.security;

import com.springboot.utils.security.MD5Utils;
import org.junit.Test;

public class MD5UtilsTest
{

	@Test
	public void encrypt()
	{
		String result = MD5Utils.encrypt("BomcAccountQuery2019-11-07 15:06:434abomc",16);

		System.out.println(result);
	}

	@Test
	public void encrypttest()
	{
		String result = MD5Utils.encrypt("BomcAccountQuery2019-11-07 15:06:434abomc");

		System.out.println(result);
	}
}