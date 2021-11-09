package com.example.GrandWorldG.util;

import java.util.Random;

/**
 * Common util methods that do not have business logic.
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class CommonUtils {

    /**
     * Generate a byte array in designated {@code length}.
     *
     * @param length Designated array length.
     * @return {@link byte[]}
     */
    public static byte[] getRandomByteArray(int length) {
        byte[] result = new byte[length];
        Random random = new Random();
        random.nextBytes(result);
        return result;
    }
}
