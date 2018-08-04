package com.haipiao.userservice.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class UserResource {

    @GET
    @Path("/email")
    public String checkEmailExistence() {
        return "email";
    }

    @GET
    @Path("/nickname")
    public String checkNickNameExistence() {
        return "nickname";
    }
    
}
