package com.example.GrandWorldG.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/*
 * Encryption and decryption with AES algorithm.
 * There are five kinds of encryption patterns, including ECB "Electronic codebook"
 * and CBC "Cipher-block chaining" and so on.
 * CBC pattern need an initialization vector.
 * <p>
 * Encrypt or decrypt in database server must has two preconditions: 1.Data must be transformed in https. 2.Admin of DB must believable.
 * So sometimes we need to do this two operations in java code.
 * 目前服务器重启后，数据库中原本被加密的数据无法解密。原因有待调查。
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class AesUtils {

    //Get a random byte array whose length is 16.
    public static final byte[] INITIALIZATION_VECTOR = CommonUtils.getRandomByteArray(16);
    //Secret key for advanced encryption standard.
    public static final String ENCRYPT_ALGORITHM = "AES";
    public static final String ENCRYPT_PATTERN = "CBC";
    public static final String ENCRYPT_PADDING = "PKCS5Padding";

    /**
     * Encrypt {@code value} in designated pattern.
     *
     * @param secretKeySeed A customised seed that is used to generate a key.
     *                      Only when the secret key seed is the same in the encryption and decryption,
     *                      can the data be encrypted and decrypted successfully.
     * @param value         Value will be encrypted.
     * @return {@link byte[]} A encrypted byte array.
     */
    public static String encryptInCbc(String value, String secretKeySeed) {
        String encryptedValue = "";
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM + "/" + ENCRYPT_PATTERN + "/" + ENCRYPT_PADDING);
            byte[] encodedKey = generateAesSecretKey(secretKeySeed);
            SecretKey secretKey = new SecretKeySpec(encodedKey, ENCRYPT_ALGORITHM);
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
     * @param secretKeySeed A customised seed that is used to generate a key.
     * @param value         a value encrypted by aes and then encoded by base64.
     * @return {@link String} A readable string.
     */
    public static String decryptInCbc(String value, String secretKeySeed) {
        String decryptedValue = "";
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM + "/" + ENCRYPT_PATTERN + "/" + ENCRYPT_PADDING);
            byte[] encodedKey = generateAesSecretKey(secretKeySeed);
            IvParameterSpec iv = new IvParameterSpec(INITIALIZATION_VECTOR);
            cipher.init(Cipher.DECRYPT_MODE, decryptSecretKey(encodedKey), iv);
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
     * @param secretKeySeed A customised seed that is used to generate a key.
     * @return A secret key.
     */
    private static byte[] generateAesSecretKey(String secretKeySeed) {
        byte[] encodedKey = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
            //SecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKeySeed.getBytes(StandardCharsets.UTF_8));
            keyGenerator.init(128, secureRandom);
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
     * @return A secret key.
     */
    private static SecretKey decryptSecretKey(byte[] encryptedSecretKey) {
        return new SecretKeySpec(encryptedSecretKey, ENCRYPT_ALGORITHM);
    }
}
