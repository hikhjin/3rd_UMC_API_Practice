package com.umc3.umc3_demo.post;

import com.umc3.umc3_demo.config.BaseException;
import com.umc3.umc3_demo.config.BaseResponseStatus;
import com.umc3.umc3_demo.post.model.PatchSalePostReq;
import com.umc3.umc3_demo.post.model.PostSalePostReq;
import com.umc3.umc3_demo.post.model.PostSalePostRes;
import org.springframework.stereotype.Service;

import java.util.prefs.BackingStoreException;

import static com.umc3.umc3_demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.umc3.umc3_demo.config.BaseResponseStatus.MODIFY_FAIL_POST;

@Service
public class PostService {
    private final PostDao postDao;
    private final PostProvider postProvider;

    public PostService(PostDao postDao, PostProvider postProvider) {
        this.postDao = postDao;
        this.postProvider = postProvider;
    }

    public PostSalePostRes createSalePosts(int userIdx, PostSalePostReq postSalePostReq) throws BaseException{
        try{
            int postIdx = postDao.insertSalePosts(userIdx, postSalePostReq.getPostTitle(), postSalePostReq.getCategory(),
                    postSalePostReq.getPrice(), postSalePostReq.getContent(), postSalePostReq.getPostImgUrl());
            return new PostSalePostRes(postIdx);
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteSalePost(int postIdx) throws BaseException{
        try{
            int result = postDao.deleteSalePost(postIdx);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifySalePost(int postIdx, PatchSalePostReq patchSalePostReq) throws BaseException{
        try{
            int result = postDao.updateSalePost(postIdx, patchSalePostReq.getPostTitle(), patchSalePostReq.getCategory(),
                    patchSalePostReq.getPrice(), patchSalePostReq.getContent(), patchSalePostReq.getPostImgUrl());

            if (result == 0){
                throw new BaseException(MODIFY_FAIL_POST);
            }
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
