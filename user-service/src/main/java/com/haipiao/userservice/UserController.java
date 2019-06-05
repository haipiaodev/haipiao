package com.haipiao.userservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haipiao.persist.entity.User;
import com.haipiao.persist.enums.Gender;
import com.haipiao.userservice.dto.CreateUserRequest;
import com.haipiao.userservice.dto.CreateUserResponse;

@Controller
public class UserController {
    public Gson gson = new Gson();
    public SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    @ResponseBody
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        return "greeting " + name;
    }

    @PostMapping("/user")
    @ResponseBody
    public String postGreeting(@RequestBody CreateUserRequest userReq) {
        User user = new User();
        CreateUserResponse resp = new CreateUserResponse();

        // TODO: validate username e.g. no duplicates
        Gender userGender = Gender.findByCode(userReq.getGender());
        if (userGender == null) {
            resp.setSuccess(false);;
            resp.setError("invalid gender format");
            return gson.toJson(resp);
        }
        user.setGender(userGender);

        Date date;
        try {
            date = formatter.parse(userReq.getBirthday());
            logger.debug("date={}", date);
        } catch (ParseException ex) {
            resp.setSuccess(false);;
            resp.setError("invalid date format");
            return gson.toJson(resp);
        }
        user.setBirthday(date);

        // TODO: persist it to DB and get an id
        int id = 2364238;

        CreateUserResponse.Data data = new CreateUserResponse.Data();
        resp.setData(data);
        data.setId(id);
        resp.setSuccess(true);
        return gson.toJson(resp);
    }
}
