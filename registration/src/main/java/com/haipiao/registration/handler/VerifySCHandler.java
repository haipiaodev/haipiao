package com.haipiao.registration.handler;

import com.haipiao.common.ErrorInfo;
import com.haipiao.common.enums.SecurityCodeType;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.common.util.security.SecurityCodeManager;
import com.haipiao.common.util.session.SessionToken;
import com.haipiao.registration.req.VerifySCRequest;
import com.haipiao.registration.resp.VerifySCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.haipiao.common.enums.ErrorCode.FORBIDDEN;

@Component
public class VerifySCHandler extends AbstractHandler<VerifySCRequest, VerifySCResponse> {

    @Autowired
    private final SecurityCodeManager securityCodeManager;

    public VerifySCHandler(SessionService sessionService,
                           SecurityCodeManager securityCodeManager) {
        super(sessionService);
        this.securityCodeManager = securityCodeManager;
    }

    @Override
    public VerifySCResponse execute(VerifySCRequest request) throws AppException {
        SecurityCodeType type = SecurityCodeType.findByCode(request.getType());
        boolean validated = securityCodeManager.validateSecurityCode(request.getCell(), request.getSecurityCode(), type);
        VerifySCResponse resp = new VerifySCResponse();
        if (!validated) {
            resp.setSuccess(false);
            resp.setErrorInfo(new ErrorInfo(FORBIDDEN, FORBIDDEN.getDefaultMessage()));
            return resp;
        }
        resp.setSuccess(true);
        SessionToken sessionToken;
        switch (type) {
            case REGISTRATION:
                // TODO: to be implemented
                sessionToken = sessionService.createTemporarySession();
                resp.setData(new VerifySCResponse.Session(sessionToken.toString(), System.currentTimeMillis()));
                break;
            case LOGIN:
                sessionToken = sessionService.createUserSession(request.getCell());
                resp.setData(new VerifySCResponse.Session(sessionToken.toString(), System.currentTimeMillis()));
                break;
            case CHANGE_PASSWORD:
                // TODO: to be implemented
                resp.setData(new VerifySCResponse.Session("****", System.currentTimeMillis()));
        }
        return resp;
    }

}
