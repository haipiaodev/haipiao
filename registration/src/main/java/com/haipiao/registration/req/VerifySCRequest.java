package com.haipiao.registration.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

public class VerifySCRequest extends AbstractRequest {

    @SerializedName("code")
    private String securityCode;

    @SerializedName("cell")
    private String cell;

    @SerializedName("type")
    private String type;

    public String getSecurityCode() {
        return securityCode;
    }

    public String getCell() {
        return cell;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "VerifySCRequest{" +
            "securityCode='" + securityCode + '\'' +
            ", cell='" + cell + '\'' +
            ", type='" + type + '\'' +
            '}';
    }
}
