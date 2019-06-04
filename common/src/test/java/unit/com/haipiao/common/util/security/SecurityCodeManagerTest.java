package com.haipiao.common.util.security;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.haipiao.common.enums.SecurityCodeType;
import com.haipiao.common.redis.RedisClientWrapper;
import com.haipiao.common.redis.RedisConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityCodeManagerTest {

    private SecurityCodeManager scManager;

    @Before
    public void setUp() {
        RedisClientWrapper redisClient = new RedisClientWrapper(new RedisConfig()
            .setHostname("localhost")
            .setPort(16379)
            .setDbIndex(0)
            .setDefaultTTL(10*60));
        scManager = new SecurityCodeManager(new Gson(), redisClient, ImmutableMap.of(
            SecurityCodeType.REGISTRATION, new SecurityCodeConfig(6, 1, 1000),
            SecurityCodeType.CHANGE_PASSWORD, new SecurityCodeConfig(6, 1, 1000)
        ));
    }

    @Test
    public void testGetAndValidateSecurityCode() {
        String cell = "8888881";
        String sc = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        assertTrue(scManager.validateSecurityCode(cell, sc, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testWrongCellNumber() {
        String cell = "8888882";
        String sc = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        assertFalse(scManager.validateSecurityCode(cell + "**", sc, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testWrongSecurityCode() {
        String cell = "8888883";
        String sc = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        assertFalse(scManager.validateSecurityCode(cell, sc + "**", SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testSecurityCodeExpires() throws Exception {
        String cell = "8888884";
        String sc = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        Thread.sleep(1500);
        assertFalse(scManager.validateSecurityCode(cell, sc, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testSuccessfulValidationShouldCleanCache() {
        String cell = "8888885";
        String sc = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        assertTrue(scManager.validateSecurityCode(cell, sc, SecurityCodeType.REGISTRATION));
        assertFalse(scManager.validateSecurityCode(cell, sc, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testThrottling() {
        String cell = "8888886";
        assertNotNull(scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION));
        assertNull(scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testThrottledAndWait() throws Exception {
        String cell = "8888887";
        assertNotNull(scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION));
        Thread.sleep(1500);
        assertNotNull(scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testThrottleNoWait() {
        String cell1 = "1888888";
        String cell2 = "2888888";
        assertNotNull(scManager.getSecurityCode(cell1, SecurityCodeType.REGISTRATION));
        assertNotNull(scManager.getSecurityCode(cell2, SecurityCodeType.REGISTRATION));
    }

    @Test
    public void testLastSecurityCodeWin() throws Exception {
        String cell = "1234567";
        String sc1 = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        Thread.sleep(1500);
        String sc2 = scManager.getSecurityCode(cell, SecurityCodeType.REGISTRATION);
        assertFalse(scManager.validateSecurityCode(cell, sc1, SecurityCodeType.REGISTRATION));
        assertTrue(scManager.validateSecurityCode(cell, sc2, SecurityCodeType.REGISTRATION));
    }

}
