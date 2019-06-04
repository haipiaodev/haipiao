package com.haipiao.registration.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.resp.AbstractResponse;

import static com.haipiao.registration.resp.VerifySCResponse.Session;

public class VerifySCResponse extends AbstractResponse<Session> {

    public static class Session {

        @SerializedName("session_token")
        private final String sessionToken;

        @SerializedName("issued_time")
        private final long issuedTime;

        public Session(String sessionToken, long issuedTime) {
            this.sessionToken = sessionToken;
            this.issuedTime = issuedTime;
        }

    }

}
