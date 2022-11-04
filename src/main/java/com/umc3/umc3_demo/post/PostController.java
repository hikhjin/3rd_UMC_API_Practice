package com.umc3.umc3_demo.post;


import com.umc3.umc3_demo.config.BaseException;
import com.umc3.umc3_demo.config.BaseResponse;
import com.umc3.umc3_demo.post.model.GetSalePostRes;
import com.umc3.umc3_demo.post.model.PatchSalePostReq;
import com.umc3.umc3_demo.post.model.PostSalePostReq;
import com.umc3.umc3_demo.post.model.PostSalePostRes;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/posts")
public class PostController {
    //final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PostProvider postProvider;
    @Autowired
    private final PostService postService;

    public PostController(PostProvider postProvider, PostService postService){
        this.postProvider = postProvider;
        this.postService = postService;
    }

    @ResponseBody
    @GetMapping("/sale") //판매 게시글 목록 조회
    public BaseResponse<List<GetSalePostRes>> getSalePosts(){
        try{
            List<GetSalePostRes> getSalePostRes = postProvider.retrieveSalePosts();
            return new BaseResponse<>(getSalePostRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/sale") //판매 게시글 등록
    public BaseResponse<PostSalePostRes> createSalePosts(@RequestBody PostSalePostReq postSalePostReq){
        try{
            PostSalePostRes postSalePostRes = postService.createSalePosts(postSalePostReq.getUserIdx(), postSalePostReq);
            return new BaseResponse<>(postSalePostRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/sale/{postIdx}/status") //판매 게시글 삭제
    public BaseResponse<String> deleteSalePost(@PathVariable("postIdx") int postIdx){
        try{
            postService.deleteSalePost(postIdx);
            String result = "게시글 삭제를 성공하였습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/sale/{postIdx}/modify") //판매 게시글 수정
    public BaseResponse<String> modifySalePost(@PathVariable("postIdx") int postIdx, @RequestBody PatchSalePostReq patchSalePostReq){
        try{
            postService.modifySalePost(postIdx, patchSalePostReq);
            String result = "게시글 수정을 성공하였습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
