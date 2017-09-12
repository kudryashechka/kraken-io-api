package com.github.joraclista.kraken;

import com.github.joraclista.kraken.auth.Auth;
import com.github.joraclista.kraken.model.request.KrakenSyncRequestImpl;
import com.github.joraclista.kraken.model.response.KrakenResponseImpl;
import junitparams.JUnitParamsRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Alisa
 * version 1.0.
 */
@RunWith(JUnitParamsRunner.class)
@Slf4j
public class SyncLossyOptimizationTest extends BaseTest {

    @Test
    public void invalidQualityLossParameter() {
        KrakenResponseImpl response = getKrakenApi().post(KrakenSyncRequestImpl.builder()
                .auth(new Auth(
                        getKrakenConfig().getKey(),
                        getKrakenConfig().getSecret()))
                .url(getImageOriginalUrl())
                .lossy(true)
                .quality(200)
                .build());

        assertFalse(response.isSuccess());
        assertNull(response.getResults());
        assertNotNull(response.getMessage());
        assertTrue(response.getMessage().contains("quality setting has to be within the range of 1-100"));
    }

    @Test
    public void qualityLossParameter() {
        KrakenResponseImpl response = getKrakenApi().post(KrakenSyncRequestImpl.builder()
                .auth(new Auth(
                        getKrakenConfig().getKey(),
                        getKrakenConfig().getSecret()))
                .url(getImageOriginalUrl())
                .lossy(true)
                .quality(50)
                .build());

        assertTrue(response.isSuccess());
        assertNull(response.getResults());

        log.info("Response: krakedUrl = {}" , response.getKrakedUrl());
    }
}