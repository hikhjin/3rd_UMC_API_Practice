package com.umc3.umc3_demo.post;


import com.umc3.umc3_demo.config.BaseException;
import com.umc3.umc3_demo.config.BaseResponseStatus;
import com.umc3.umc3_demo.post.model.GetSalePostRes;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import static com.umc3.umc3_demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class PostProvider {

    private final PostDao postDao;

    //final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PostProvider(PostDao postDao){
        this.postDao = postDao;
    }

    public List<GetSalePostRes> retrieveSalePosts() throws BaseException{
        try{
            List<GetSalePostRes> getSalePostRes = postDao.selectSalePosts();
            return getSalePostRes;
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
