package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.constants.RecommendationArticleConstant;
import com.haipiao.articleservice.constants.RecommendationArticleTimeConstant;
import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ArticleTopicRepository;
import com.haipiao.persist.utils.DateTimeUtil;
import com.haipiao.persist.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class CommonRecommendationArticle {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    @Autowired
    private RecommendationByNearby recommendationByNearby;

    @Autowired
    private RecommendationByDiscover recommendationByDiscover;

    @Autowired
    private RecommendationByArticleRelated recommendationByArticleRelated;

    @Autowired
    private RecommendationByTopicRelated recommendationByTopicRelated;

    @Autowired
    private RecommendationByTopicRelatedLatest recommendationByTopicRelatedLatest;

    public List<RecommendationArticleResponse.Data.ArticleData> getArticleDataList(RecommendationArticleRequest request){
        String context = request.getContext();
        switch (context){
            case RecommendationArticleConstant.DISCOVER:
                return recommendationByDiscover.assemblerDate(request);
            case RecommendationArticleConstant.NEARBY:
                return recommendationByNearby.assemblerDate(request);
            case RecommendationArticleConstant.ARTICLE_RELATED:
                return recommendationByArticleRelated.assemblerDate(request);
            case RecommendationArticleConstant.TOPIC_RELATED:
                return recommendationByTopicRelated.assemblerDate(request);
            case RecommendationArticleConstant.TOPIC_RELATED_LATEST:
                return recommendationByTopicRelatedLatest.assemblerDate(request);
            default:
                return null;
        }
    }

    public List<Article> getArticlesOfTopicCommonMethod(int days, int cursor, int limit){
        List<Integer> articleIds = articleTopicRepository.getArticlesOfHotTopic(DateTimeUtil.someHoursAgo(days), DateTimeUtil.dateToString(new Date()), cursor, limit);
        return getArticlesCommonMethod(articleIds);
    }

    public List<Article> getArticlesCommonMethod(List<Integer> articleIds){
        return StreamSupport.stream(articleRepository.findAllById(articleIds).spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
