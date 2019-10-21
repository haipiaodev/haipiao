package com.haipiao.articleservice.dto.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.resp.AbstractResponse;

public class CreateArticleResponse extends AbstractResponse<CreateArticleResponse.Data> {

    public static class Data {
        @SerializedName("id")
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    
}
