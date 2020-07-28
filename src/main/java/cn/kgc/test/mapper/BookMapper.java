package cn.kgc.test.mapper;

import cn.kgc.test.model.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);


    List<Book> selectListByBook(Book book);


    List<Book> selectListByCategoryIdAndKeywords(@Param("categoryId") Integer categoryId, @Param("keywords") String keywords);
}