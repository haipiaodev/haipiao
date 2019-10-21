package com.haipiao.persist.enums;

import java.util.Arrays;

public enum ArticleStatus {

    PENDING(0), PUBLISHED(1), DELETE_PENDING(2), DELETED(3);

    ArticleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ArticleStatus findByCode(int code) {
        return Arrays.stream(ArticleStatus.values())
                .filter(e -> e.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    private int code;
}
