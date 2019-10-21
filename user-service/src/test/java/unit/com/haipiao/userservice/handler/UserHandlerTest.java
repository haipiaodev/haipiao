package com.haipiao.userservice.handler;

import com.haipiao.persist.config.PersistConfig;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.req.CreateUserRequest;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.CreateUserResponse;
import com.haipiao.userservice.resp.GetUserResponse;
import org.assertj.core.util.Preconditions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = {UserHandlerTest.Config.class, PersistConfig.class})
public class UserHandlerTest {

    @Configuration
    static class Config {
        @Bean
        public CreateUserHandler createUserHandler(@Autowired UserRepository userRepository) {
            return new CreateUserHandler(userRepository);
        }

        @Bean
        public GetUserHandler getUserHandler(@Autowired UserRepository userRepository) {
            return new GetUserHandler(userRepository);
        }
    }

    @Autowired
    private CreateUserHandler createUserHandler;

    @Autowired
    private GetUserHandler getUserHandler;

    @Before
    public void setUp() {
        Preconditions.checkNotNull(createUserHandler);
        Preconditions.checkNotNull(getUserHandler);
    }

    @Test
    public void testCreateUserHandler() {
        CreateUserRequest createReq = new CreateUserRequest();
        createReq.setName("Alice");
        createReq.setBirthday("31/12/1999");
        createReq.setGender("F");
        CreateUserResponse createResp = createUserHandler.handle(createReq);
        assertTrue(createResp.getSuccess());
        assertNotNull(createResp.getData().getId());

        int id = createResp.getData().getId();
        GetUserRequest getReq = new GetUserRequest();
        getReq.setId(id);
        GetUserResponse getResp = getUserHandler.handle(getReq);
        assertTrue(getResp.getSuccess());
        assertEquals(getResp.getData().getName(), "Alice");
    }
}
