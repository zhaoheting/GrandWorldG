package com.example.GrandWorldG.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Encryption and decryption with AES algorithm.
 * There are five kinds of encryption patterns, including ECB "Electronic codebook"
 * and CBC "Cipher-block chaining" and so on.
 * CBC pattern need an initialization vector.
 * <p>
 * Encrypt or decrypt in database server must has two preconditions: 1.Data must be transformed in https. 2.Admin of DB must believable.
 * So sometimes we need to do this two operations in java code.
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class AesUtils {

    //Get a random byte array whose length is 16.
    public static final byte[] INITIALIZATION_VECTOR = CommonUtils.getRandomByteArray(16);
    //Secret key for advanced encryption standard.
    public static final byte[] SECRET_KEY = CommonUtils.getRandomByteArray(16);
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * Encrypt {@code value} in designated pattern.
     *
     * @param value Value will be encrypted.
     * @return {@link byte[]} A encrypted byte array.
     */
    public static String encryptInCbc(String value) {
        String encryptedValue = "";
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY, "AES");
            IvParameterSpec iv = new IvParameterSpec(INITIALIZATION_VECTOR);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            encryptedValue = new String(Base64.getEncoder().encode(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | InvalidAlgorithmParameterException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedValue;
    }

    /**
     * Decrypt a value encoded by base64.
     *
     * @param value a value encrypted by aes and then encoded by base64.
     * @return {@link String} A readable string.
     */
    public static String decryptInCbc(String value) {
        String decryptedValue = "";
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY, "AES");
            IvParameterSpec iv = new IvParameterSpec(INITIALIZATION_VECTOR);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            decryptedValue = new String(cipher.doFinal(Base64.getDecoder().decode(value)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptedValue;
    }

    /**
     * Generate a secret key that is encrypted by the designated algorithm.
     * Sometimes the secret key need to be encrypted and then transformed.
     *
     * @param algorithm
     * @return
     */
    public static byte[] generateAesSecretKey(String algorithm) {
        byte[] encodedKey = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(128);
            encodedKey = keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodedKey;
    }

    /**
     * Decrypt the secret key.
     *
     * @param encryptedSecretKey Secret key that is encrypted by an algorithm.
     * @param algorithm
     * @return
     */
    public static SecretKey decryptSecretKey(byte[] encryptedSecretKey, String algorithm) {
        return new SecretKeySpec(encryptedSecretKey, algorithm);
    }
}
