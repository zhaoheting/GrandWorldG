package com.example.GrandWorldG.service;

import javax.crypto.SecretKey;

/**
 * TODO
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public interface AESEncryptionService {

    byte[] encrypt(String value, SecretKey secretKey);

    String decrypt(String value, SecretKey secretKey);
}
