package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.BookMapper;
import cn.kgc.test.model.Book;
import cn.kgc.test.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 11:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    /**
     * 根据分类信息查询图书
     *
     * @param categoryId
     * @return
     */
    @Override
    public Map<String, Object> getListByCategoryIdAndKeywords(Integer categoryId, String keywords, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<Book> all = new PageInfo<Book>(bookMapper.selectListByCategoryIdAndKeywords(categoryId, keywords));
        map.put("total", all.getTotal());
        map.put("data", all.getList());
        return map;
    }


    /**
     * 添加书籍
     *
     * @param book
     * @return
     */
    @Override
    public Integer addBooks(Book book) {
        return bookMapper.insert(book);
    }

    /**
     * 更新书籍
     *
     * @param book
     * @return
     */
    @Override
    public Integer updBooks(Book book) {
        return bookMapper.updateByPrimaryKey(book);
    }


    /**
     * 删除书籍
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteBooksByPrimaryKey(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

}
