package com.example.GrandWorldG.service.impl;

import com.example.GrandWorldG.service.AESEncryptionService;

import javax.crypto.SecretKey;

/**
 * TODO
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class AESEncryptionServiceImpl implements AESEncryptionService {

    private final ConfigService configService;

    public AESEncryptionServiceImpl(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public byte[] encrypt(String value, SecretKey secretKey) {
        return new byte[0];
    }

    @Override
    public String decrypt(String value, SecretKey secretKey) {
        return null;
    }
}
