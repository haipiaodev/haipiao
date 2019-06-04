package com.haipiao.common.handler;

public interface Handler<Req, Res> {

    Res handle(Req request);

}
