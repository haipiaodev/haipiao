package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.ArticleResponse;
import com.haipiao.articleservice.service.GetArticleCommonService;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ImageRepository;
import com.haipiao.persist.vo.ArticleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangshun
 */
@Component
public class GetUserCollectionHandler extends AbstractHandler<GetArticleCommentsRequest, ArticleResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserCollectionHandler.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Resource
    private GetArticleCommonService getArticleCommonService;

    @Autowired
    private ImageRepository imageRepository;

    protected GetUserCollectionHandler(SessionService sessionService,
                                       ArticleRepository articleRepository,
                                       ImageRepository imageRepository) {
        super(ArticleResponse.class, sessionService);
        this.articleRepository = articleRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ArticleResponse execute(GetArticleCommentsRequest request) throws AppException {
        ArticleResponse resp = new ArticleResponse(StatusCode.SUCCESS);
        Integer userId = request.getId();
        Pageable pageable = PageRequest.of(Integer.valueOf(request.getCursor()), request.getLimit());
        Page<Article> articlesPage = articleRepository.findArticlesByCollectorIdAndStatus(userId, "", pageable);
        List<Article> articles = articlesPage.getContent();
        if (CollectionUtils.isEmpty(articles)) {
            resp.setErrorMessage(String.format("user %s not have collection article", userId));
            return resp;
        }

        List<ArticleData> articlesList = articles.stream()
                .filter(Objects::nonNull)
                .map(a -> new ArticleData(imageRepository.findFirstByArticleId(a.getArticleId(),0).getExternalUrl(),
                        a.getArticleId(), a.getTitle(), a.getLikes(), getArticleCommonService.checkIsLike(a.getArticleId(), userId),
                        getArticleCommonService.assemblerAuthor(userId)))
                .collect(Collectors.toList());

        resp.setData(new ArticleResponse.Data(articlesList, (int) articlesPage.getTotalElements(),
                request.getCursor()+articles.size(), articlesPage.getTotalPages() > Integer.valueOf(request.getCursor())));
        return resp;
    }
}
