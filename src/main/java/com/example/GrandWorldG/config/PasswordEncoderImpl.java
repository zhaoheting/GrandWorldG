package com.example.GrandWorldG.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * It is essential to implement this interface when we define a user and set the password for it.
 *
 * @author Hobbs.Heting.Zhao
 * @since 11/12/2021
 */
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
