package com.haipiao.persist.repository;

import java.util.List;

import com.haipiao.persist.entity.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    List<Tag> findAllByImageId(int articleID);
}
