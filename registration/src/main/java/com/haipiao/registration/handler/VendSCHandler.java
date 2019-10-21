package com.haipiao.registration.handler;


import com.haipiao.common.ErrorInfo;
import com.haipiao.common.enums.SecurityCodeType;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.common.util.security.SecurityCodeManager;
import com.haipiao.registration.req.VendSCRequest;
import com.haipiao.registration.resp.VendSCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.haipiao.common.enums.ErrorCode.THROTTLED;

@Component
public class VendSCHandler extends AbstractHandler<VendSCRequest, VendSCResponse> {

    @Autowired
    private final SecurityCodeManager securityCodeManager;

    public VendSCHandler(SessionService sessionService,
                         SecurityCodeManager securityCodeManager) {
        super(sessionService);
        this.securityCodeManager = securityCodeManager;
    }

    @Override
    public VendSCResponse execute(VendSCRequest request) {
        String sc = securityCodeManager.getSecurityCode(
            request.getCell(), SecurityCodeType.findByCode(request.getType()));
        var resp = new VendSCResponse();
        if (sc != null) {
            resp.setSuccess(true);
            resp.setSecurityCode(sc);
        } else {
            resp.setSuccess(false);
            resp.setErrorInfo(new ErrorInfo(THROTTLED, THROTTLED.getDefaultMessage()));
        }
        return resp;
    }
}
