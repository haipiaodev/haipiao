package com.haipiao.articleservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.articleservice.dto.req.CreateArticleRequest;
import com.haipiao.articleservice.dto.req.GetArticleRequest;
import com.haipiao.articleservice.dto.resp.CreateArticleResponse;
import com.haipiao.articleservice.dto.resp.GetArticleResponse;
import com.haipiao.articleservice.handler.CreateArticleHandler;
import com.haipiao.articleservice.handler.GetArticleHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private GetArticleHandler getArticleHandler;

    @Autowired
    private CreateArticleHandler createArticleHandler;

    @GetMapping("/article/{articleID}")
    @ResponseBody
    public ResponseEntity<GetArticleResponse> getArticle(@PathVariable(value="articleID") String articleID) {
        logger.info("articleID={}", articleID);
        Preconditions.checkArgument(StringUtils.isNotEmpty(articleID));
        GetArticleRequest req = new GetArticleRequest();
        return getArticleHandler.handle(req);
    }

    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<CreateArticleResponse> createArticle(@RequestBody CreateArticleRequest req) {
        // TODO: get author ID from session token.
        req.setAuthorId(1);
        return createArticleHandler.handle(req);
    }
}
