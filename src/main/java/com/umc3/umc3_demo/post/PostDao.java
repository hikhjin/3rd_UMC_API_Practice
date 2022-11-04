package com.umc3.umc3_demo.post;

import com.umc3.umc3_demo.post.model.GetSalePostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.function.ObjDoubleConsumer;


@Repository
public class PostDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetSalePostRes> selectSalePosts(){
        String selectSalePostsQuery =
                "SELECT S.postIdx, U.userIdx, U.nickName, S.postTitle, S.price, U.myTown, S.postImgUrl, S.createdAt FROM SalePost S JOIN User U on S.userIdx = U.userIdx WHERE S.status = 'ACTIVE'";

        return this.jdbcTemplate.query(selectSalePostsQuery,
                (rs, rowNum) -> new GetSalePostRes(
                        rs.getInt("postIdx"),
                        rs.getInt("userIdx"),
                        rs.getString("nickName"),
                        rs.getString("postTitle"),
                        rs.getInt("price"),
                        rs.getString("myTown"),
                        rs.getString("postImgUrl"),
                        rs.getString("createdAt")
                ));

    }
    public int insertSalePosts(int userIdx, String postTitle, String category, int price, String content, String postImgUrl){
        String insertSalePostsQuery = "INSERT INTO SalePost(userIdx, postTitle, category, price, content, postImgUrl) VALUES (?, ?, ?, ?, ?, ?);";
        Object[] insertSalePostsParams = new Object[]{userIdx, postTitle, category, price, content, postImgUrl};
        this.jdbcTemplate.update(insertSalePostsQuery, insertSalePostsParams);

        String lastInsertIdxQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdxQuery, int.class);
    }

    public int deleteSalePost(int postIdx){
        String deleteSalePostQuery = "UPDATE SalePost SET status = 'DELETED' WHERE postIdx = ?";
        Object [] deleteSalePostParams = new Object[] {postIdx};

        return this.jdbcTemplate.update(deleteSalePostQuery, deleteSalePostParams);
    }

    public int updateSalePost(int postIdx, String postTitle, String category, int price, String content, String postImgUrl){
        String updateSalePostQuery = "UPDATE SalePost SET postTitle=?, category=?, price=?, content=?, postImgUrl=? WHERE postIdx = ?";
        Object [] updateSalePostParams = new Object[]{
                postTitle, category, price, content, postImgUrl, postIdx
        };
        return this.jdbcTemplate.update(updateSalePostQuery, updateSalePostParams);
    }

}
