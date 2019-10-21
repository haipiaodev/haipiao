package com.haipiao.common.util.session;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Base64;

public class SessionToken {

    private byte[] selector;  // 16 bytes

    private byte[] validator; // 16 bytes

    public byte[] getSelector() {
        return selector;
    }

    public void setSelector(byte[] selector) {
        this.selector = selector;
    }

    public byte[] getValidator() {
        return validator;
    }

    public void setValidator(byte[] validator) {
        this.validator = validator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionToken that = (SessionToken) o;
        return Arrays.equals(selector, that.selector) &&
            Arrays.equals(validator, that.validator);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(selector);
        result = 31 * result + Arrays.hashCode(validator);
        return result;
    }

    @Override
    public String toString() {
        Preconditions.checkNotNull(selector, "Null selector");
        Preconditions.checkNotNull(validator, "Null validator");
        return Base64.getEncoder().encodeToString(selector) + ":" + Base64.getEncoder().encodeToString(validator);
    }
}
