package com.haipiao.persist.repository;

import com.haipiao.persist.config.PersistConfig;
import com.haipiao.persist.config.TestConfig;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.entity.UserSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = {PersistConfig.class, TestConfig.class})
public class UserSessionRepositoryTest {

    @Autowired
    UserSessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestObjectsFactory testObjectsFactory;

    @Test
    public void testSaveAndRead() {
        User user = userRepository.save(testObjectsFactory.createDefaultUser());
        UserSession session = testObjectsFactory.createUserSession(user.getUserId());
        userRepository.save(user);
        UserSession expected = sessionRepository.save(session);
        UserSession actual = sessionRepository.findBySelector(session.getSelector());
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

}
