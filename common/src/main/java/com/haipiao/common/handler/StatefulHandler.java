package com.haipiao.common.handler;

import org.springframework.http.ResponseEntity;

public interface StatefulHandler<ReqT, RespT> {

    ResponseEntity<RespT> handle(String sessionToken, ReqT request);

}
