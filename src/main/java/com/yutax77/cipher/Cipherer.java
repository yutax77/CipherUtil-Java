package com.yutax77.cipher;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Cipherer {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	private static final String ALGORITHM = "Blowfish";
	
	public static String encrypt(String key, String text) 
			throws NoSuchAlgorithmException,
			NoSuchPaddingException,
			InvalidKeyException,
			IllegalBlockSizeException,
			BadPaddingException{
		SecretKeySpec spec = new SecretKeySpec(key.getBytes(UTF8), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, spec);
		byte[] encrypted = cipher.doFinal(text.getBytes(UTF8));
		
		return Hex.encodeHexString(encrypted);
	}
	
	public static String decrypt(String key, String text) 
			throws DecoderException,
			NoSuchAlgorithmException,
			NoSuchPaddingException,
			InvalidKeyException,
			IllegalBlockSizeException,
			BadPaddingException{
		byte[] encrypted = Hex.decodeHex(text.toCharArray());
		
		SecretKeySpec spec = new SecretKeySpec(key.getBytes(UTF8), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, spec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted, UTF8);
	}
}
