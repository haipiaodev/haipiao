package com.haipiao.common.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.req.AbstractRequest;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.common.service.SessionService;
import com.haipiao.common.util.session.UserSessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public abstract class AbstractHandler<ReqT extends AbstractRequest, RespT extends AbstractResponse>
    implements Handler<ReqT, RespT>, StatefulHandler<ReqT, RespT> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHandler.class);

    protected final SessionService sessionService;

    protected AbstractHandler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public ResponseEntity<RespT> handle(ReqT request) {
        return handle(null, request, false);
    }

    @Override
    public ResponseEntity<RespT> handle(String sessionToken, ReqT request) {
        return handle(sessionToken, request, true);
    }

    private ResponseEntity<RespT> handle(String sessionToken, ReqT request, boolean sessionRequired) {
        try {
            if (sessionRequired) {
                UserSessionInfo sessionInfo = sessionService.validateSession(sessionToken);
                request.setLoggedInUserId(sessionInfo.getUserId());
            }
            return ResponseEntity.ok(execute(request));
        } catch (Exception ex) {
            LOGGER.error("Exception happened while handling request.", ex);
            // TODO: should return a response object with proper error code and error message
            throw new RuntimeException(ex);
        }
    }

    protected abstract RespT execute(ReqT request) throws AppException;

}
