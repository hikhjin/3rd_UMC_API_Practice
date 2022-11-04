package com.umc3.umc3_demo.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GetSalePostRes {
    private int postIdx;
    private int userIdx;
    private String nickName;
    private String postTitle;
    private int price;
    private String myTown;
    private String postImgUrl;
    private String createdAt;
}
