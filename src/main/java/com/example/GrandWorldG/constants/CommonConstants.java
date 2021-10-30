package com.example.GrandWorldG.constants;

import com.example.GrandWorldG.util.AesUtils;
import com.example.GrandWorldG.util.CommonUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Common constants.
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class CommonConstants {
    //IV(Initialization vector)?????????CBC????????????????????
    //????????????????(???128?????16?byte????????256)
    //Get a random byte array whose length is 16.
    public static final byte[] INITIALIZATION_VECTOR_FOR_AES_CBC_PATTERN = CommonUtils.getRandomByteArray(16);
    //Secret key for advanced encryption standard.
    public static final byte[] secretkey = "Hobbs.Heting.Zhao".getBytes(StandardCharsets.UTF_8);
}
