package com.haipiao.userservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.userservice.handler.CreateUserHandler;
import com.haipiao.userservice.handler.GetUserHandler;
import com.haipiao.userservice.req.CreateUserRequest;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.CreateUserResponse;
import com.haipiao.userservice.resp.GetUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private CreateUserHandler createUserHandler;

    @GetMapping("/user/{userID}/summary")
    @ResponseBody
    public ResponseEntity<GetUserResponse> getUser(@PathVariable(value="userID") String userID) {
        logger.info("userID={}", userID);
        Preconditions.checkArgument(StringUtils.isNotEmpty(userID));

        GetUserRequest req = new GetUserRequest().setId(Integer.parseInt(userID));
        return getUserHandler.handle(req);
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest req) {
        return createUserHandler.handle(req);
    }
}
