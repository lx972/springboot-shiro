package cn.kgc.test.service;

import cn.kgc.test.model.Book;


import java.util.Map;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 11:43
 */
public interface BookService {

    /**
     * 根据分类信息查询图书
     *
     * @param categoryId
     * @return
     */
    Map<String, Object> getListByCategoryIdAndKeywords(Integer categoryId, String keywords, Integer currentPage, Integer pageSize);


    /**
     * 添加书籍
     *
     * @param book
     * @return
     * @throws Exception
     */
    Integer addBooks(Book book);


    /**
     * 更新书籍
     *
     * @param book
     * @return
     * @throws Exception
     */
    Integer updBooks(Book book);

    /**
     * 删除书籍
     *
     * @param id
     * @return
     */
    Integer deleteBooksByPrimaryKey(Integer id);

}
