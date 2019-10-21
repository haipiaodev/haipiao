package com.haipiao.common.handler;

import org.springframework.http.ResponseEntity;

public interface Handler<ReqT, RespT> {

    ResponseEntity<RespT> handle(ReqT request);

}
