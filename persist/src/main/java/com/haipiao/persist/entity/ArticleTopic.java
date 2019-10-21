package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "article_topic_relation")
public class ArticleTopic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // TODO: is it OK to use int while it's UUID in the schema.

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "topic_id")
    private int topicId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleTopic that = (ArticleTopic) o;
        return id == that.id &&
                articleId == that.articleId &&
                topicId == that.topicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleId, topicId);
    }

    @Override
    public String toString() {
        return "ArticleTopic{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", topicId=" + topicId +
                '}';
    }
}
