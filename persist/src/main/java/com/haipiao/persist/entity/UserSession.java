package com.haipiao.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "user_session")
public class UserSession extends BaseEntity {

    @Id
    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "selector")
    private byte[] selector; // 16 bytes selector

    @Column(name = "validator_digest")
    private byte[] validatorDigest; // 64 bytes validator digest

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getSelector() {
        return selector;
    }

    public void setSelector(byte[] selector) {
        this.selector = selector;
    }

    public byte[] getValidatorDigest() {
        return validatorDigest;
    }

    public void setValidatorDigest(byte[] validatorDigest) {
        this.validatorDigest = validatorDigest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession session = (UserSession) o;
        return sessionId == session.sessionId &&
            userId == session.userId &&
            Arrays.equals(selector, session.selector) &&
            Arrays.equals(validatorDigest, session.validatorDigest);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sessionId, userId);
        result = 31 * result + Arrays.hashCode(selector);
        result = 31 * result + Arrays.hashCode(validatorDigest);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserSession.class.getSimpleName() + "[", "]")
            .add("sessionId=" + sessionId)
            .add("userId=" + userId)
            .add("selector=" + Arrays.toString(selector))
            .add("validatorDigest=" + Arrays.toString(validatorDigest))
            .toString();
    }
}
