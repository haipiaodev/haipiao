package com.haipiao.persist.repository;

import java.util.List;
import com.haipiao.persist.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findByArticleIdOrderByPositionIdxAsc(int articleID);
}
