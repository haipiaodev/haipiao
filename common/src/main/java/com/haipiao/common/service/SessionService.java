package com.haipiao.common.service;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.util.session.UserSessionInfo;

public interface SessionService {

    UserSessionInfo validateSession(String sessionToken) throws AppException;

}
