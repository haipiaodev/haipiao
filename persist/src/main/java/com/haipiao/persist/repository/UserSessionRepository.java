package com.haipiao.persist.repository;

import com.haipiao.persist.entity.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, Integer> {

    UserSession findBySelector(byte[] selector);

}
