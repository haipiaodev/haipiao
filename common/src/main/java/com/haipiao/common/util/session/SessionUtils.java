package com.haipiao.common.util.session;


import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class SessionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtils.class);

    private static final String SESSION_TOKEN_DELIMITER = ":";
    private static final Random RANDOM = new Random();

    public static SessionToken parseSessionToken(String sessionToken) {
        String[] segments = sessionToken.split(SESSION_TOKEN_DELIMITER);
        Preconditions.checkArgument(segments.length == 2, "Mal-formatted session token");
        byte[] selector = Base64.getDecoder().decode(segments[0]);
        byte[] validator = Base64.getDecoder().decode(segments[1]);
        SessionToken parsedSessionToken = new SessionToken();
        parsedSessionToken.setSelector(selector);
        parsedSessionToken.setValidator(validator);
        return parsedSessionToken;
    }

    public static SessionToken generateSessionToken() {
        byte[] token = new byte[32];
        RANDOM.nextBytes(token);
        byte[] selector = Arrays.copyOfRange(token, 0, 16);
        byte[] validator = Arrays.copyOfRange(token, 0, 16);
        SessionToken sessionToken = new SessionToken();
        sessionToken.setSelector(selector);
        sessionToken.setValidator(validator);
        return sessionToken;
    }

    public static byte[] getValidatorDigest(byte[] validator) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("Not implementation of SHA-256 found");
            throw new RuntimeException(ex);
        }
        return digest.digest(validator);
    }

}
