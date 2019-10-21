package com.haipiao.userservice.application;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haipiao.userservice.handler.CreateUserHandler;
import com.haipiao.userservice.handler.GetUserHandler;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.GetUserResponse;
import com.haipiao.userservice.req.CreateUserRequest;
import com.haipiao.userservice.resp.CreateUserResponse;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private CreateUserHandler createUserHandler;

    @GetMapping("/user/{userID}/summary")
    @ResponseBody
    public GetUserResponse getUser(@PathVariable(value="userID") String userID) {
        logger.info("userID={}", userID);
        Preconditions.checkArgument(StringUtils.isNotEmpty(userID));

        GetUserRequest req = new GetUserRequest().setId(Integer.parseInt(userID));
        return getUserHandler.handle(req);
    }

    @PostMapping("/user")
    @ResponseBody
    public CreateUserResponse createUser(@RequestBody CreateUserRequest req) {
        return createUserHandler.handle(req);
    }
}
