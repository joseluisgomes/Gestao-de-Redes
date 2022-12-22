package cryptography;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class CipherAES {
    private static final String IV = "AAAAAAAAAAAAAAAA";
    private static final String RSAKey = "0123456789abcdef";

    public static byte[] encrypt(String cleanText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(RSAKey.getBytes(UTF_8), "AES");

        cipher.init(ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(UTF_8)));
        return cipher.doFinal(cleanText.getBytes(UTF_8));
    }

    public static String decrypt(byte[] encryptedText) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(RSAKey.getBytes(UTF_8), "AES");

        cipher.init(DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(UTF_8)));
        return new String(cipher.doFinal(encryptedText), UTF_8);
    }
}
