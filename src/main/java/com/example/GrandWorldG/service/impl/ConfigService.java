package com.example.GrandWorldG.service.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * Service that provides configuration properties.
 *
 * @author Hobbs.Heting.Zhao
 * @since 10/29/2021
 */
public class ConfigService {

    @Value("${domainUsername:Hobbs.Heting.Zhao}")
    private String domainUsername;

    @Value("${aes.cbc.blockLength:16}")
    private int cbcBlockLength;

    public int getCbcBlockLength() {
        return cbcBlockLength;
    }

    public String getDomainUsername() {
        return domainUsername;
    }
}
