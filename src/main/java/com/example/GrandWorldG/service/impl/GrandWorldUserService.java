package com.example.GrandWorldG.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A user authentication service of spring security.
 *
 * @author Hobbs.Heting.Zhao
 * @since 11/12/2021
 */
@Service
public class GrandWorldUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
