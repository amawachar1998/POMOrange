package practice;

import java.util.Base64;

public class DecodePassword {

	public static void main(String[] args) {

//		String password = "aGVsbG8";// hello
		String password = "aGVsbG8=";// hello
		System.out.println(password);
		System.out.println(decryptData(password));
		String password2 = "hello";// hello

		System.out.println((password2));
		System.out.println(encryptData(password2));

	}

	public static String decryptData(String decrptData) {
		byte[] decodeBytes = Base64.getDecoder().decode(decrptData.getBytes());
		return (new String(decodeBytes));
	}

	public static String encryptData(String decrptData) {
		byte[] encode = Base64.getEncoder().encode(decrptData.getBytes());
		return (new String(encode));

	}

}
