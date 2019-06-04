package com.haipiao.registration.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

public class VendSCRequest extends AbstractRequest {

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("cell")
    private String cell;

    @SerializedName("type")
    private String type;

    public VendSCRequest setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public VendSCRequest setCell(String cell) {
        this.cell = cell;
        return this;
    }

    public VendSCRequest setType(String type) {
        this.type = type;
        return this;
    }

    public String getCell() {
        return cell;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getType() {
        return type;
    }
}
