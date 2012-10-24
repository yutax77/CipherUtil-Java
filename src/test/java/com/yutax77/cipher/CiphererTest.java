package com.yutax77.cipher;

import static org.junit.Assert.*;

import javax.crypto.BadPaddingException;

import org.junit.Test;

public class CiphererTest {
	@Test(expected=BadPaddingException.class)
	public void testEncryptAndDescrypt() throws Exception {
		String enc = Cipherer.encrypt("hoge", "java");
		assertNotNull(enc);
		assertFalse("java".equals(enc));
		System.out.println(enc);
		
		//復号化
		String dec = Cipherer.decrypt("hoge", enc);
		assertNotNull(dec);
		assertEquals("java", dec);
		
		//キーが違うので例外
		Cipherer.decrypt("foo", enc);
	}

	@Test
	public void testEncryptAndDescryptWithEmptyTarget() throws Exception {
		String enc = Cipherer.encrypt("hoge", "");
		assertNotNull(enc);
		assertFalse("".equals(enc));
		System.out.println(enc);
		
		//復号化
		String dec = Cipherer.decrypt("hoge", enc);
		assertNotNull(dec);
		assertEquals("", dec);
	}
}
