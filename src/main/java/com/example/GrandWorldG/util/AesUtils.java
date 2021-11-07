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
import java.util.Objects;

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
    public static final byte[] INITIALIZATION_VECTOR = "hozhahozhahozha1".getBytes(StandardCharsets.UTF_8);
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
            Cipher cipher = generateCipher(Cipher.ENCRYPT_MODE, secretKeySeed);
            encryptedValue = new String(Base64.getEncoder().encode(Objects.requireNonNull(cipher).doFinal(value.getBytes(StandardCharsets.UTF_8))));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
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
            Cipher cipher = generateCipher(Cipher.DECRYPT_MODE, secretKeySeed);
            assert cipher != null;
            decryptedValue = new String(cipher.doFinal(Base64.getDecoder().decode(value)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptedValue;
    }

    /**
     * Generate a secret key that is encrypted by the designated algorithm.
     * Sometimes the secret key need to be encrypted and then transformed.
     *
     * @param secretKeySeed A customised seed that is used to generate a key.
     * @param mode
     * @return A secret key.
     */
    private static Cipher generateCipher(int mode, String secretKeySeed) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(ENCRYPT_ALGORITHM + "/" + ENCRYPT_PATTERN + "/" + ENCRYPT_PADDING);
            byte[] secretKey = generateSecretKey(secretKeySeed);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, ENCRYPT_ALGORITHM);
            //Here we choose to define iv and secret key into same value. I am not sure this is a perfect choice.
            IvParameterSpec iv = new IvParameterSpec(secretKey);
            cipher.init(mode, secretKeySpec, iv);
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateSecretKey(String secretKeySeed) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
        //SecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
        //使用随机方法生成的密钥为什么每次都一样呢？
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKeySeed.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey().getEncoded();
    }
}
