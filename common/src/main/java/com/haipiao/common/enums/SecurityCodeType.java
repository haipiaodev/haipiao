package com.haipiao.common.enums;

import java.util.Arrays;

public enum SecurityCodeType {

    REGISTRATION("registration"), CHANGE_PASSWORD("password"), LOGIN("login");

    SecurityCodeType(String code) {
        this.code = code;
    }

    public static SecurityCodeType findByCode(String code) {
        return Arrays.stream(SecurityCodeType.values())
            .filter(sc -> code.equalsIgnoreCase(sc.getCode()))
            .findFirst()
            .orElse(null);
    }

    public String getCode() {
        return code;
    }

    private final String code;

}
