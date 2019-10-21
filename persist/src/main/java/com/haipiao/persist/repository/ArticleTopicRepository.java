package com.haipiao.persist.repository;

import java.util.List;

import com.haipiao.persist.entity.ArticleTopic;
import org.springframework.data.repository.CrudRepository;

public interface ArticleTopicRepository extends CrudRepository<ArticleTopic, Integer> {
    List<ArticleTopic> findAllByArticleId(int articleID);
    List<ArticleTopic> findAllByTopicId(int topicID);
}
