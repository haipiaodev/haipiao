package com.haipiao.persist.repository;

import com.haipiao.persist.config.PersistConfig;
import com.haipiao.persist.config.TestConfig;
import com.haipiao.persist.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = {PersistConfig.class, TestConfig.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestObjectsFactory testObjectsFactory;

    @Test
    public void testSaveAndRead() {
        User user = testObjectsFactory.createDefaultUser();
        User expected = userRepository.save(user);
        Optional<User> actual = userRepository.findById(user.getUserId());
        assertNotNull(actual.get());
        assertEquals(expected, actual.get());
    }

}
