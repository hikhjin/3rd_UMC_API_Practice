package com.umc3.umc3_demo.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostSalePostReq {
    private int userIdx;
    private String postTitle;
    private String category;
    private int price;
    private String content;
    private String postImgUrl;
}
