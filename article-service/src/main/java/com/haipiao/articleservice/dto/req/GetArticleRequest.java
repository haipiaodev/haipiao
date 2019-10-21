package com.haipiao.articleservice.dto.req;

import com.haipiao.common.req.AbstractRequest;

public class GetArticleRequest extends AbstractRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
