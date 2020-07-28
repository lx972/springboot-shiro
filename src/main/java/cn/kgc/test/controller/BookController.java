package cn.kgc.test.controller;

import cn.kgc.test.model.Book;
import cn.kgc.test.service.BookService;
import cn.kgc.test.utils.QiniuConfig;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 11:50
 */
@RestController
@RequestMapping(path = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private QiniuConfig qiniuConfig;

    /**
     * 根据分类查
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/category/book/{id}", method = RequestMethod.GET)
    public Map<String, Object> getAllBookByCategoryId(@PathVariable(name = "id") Integer id, String keywords, Integer currentPage, Integer pageSize) {
        System.out.println("currentPage" + currentPage);
        System.out.println("pageSize" + pageSize);
        System.out.println("keywords" + keywords);
        Map<String, Object> result = bookService.getListByCategoryIdAndKeywords(id, keywords, currentPage, pageSize);
        result.put("code", 200);
        result.put("msg", "查询成功");
        return result;
    }


    /**
     * 添加书籍
     *
     * @param book
     * @return
     */
    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public ResultAPI addBooks(@RequestBody Book book) {
        bookService.addBooks(book);
        return new ResultAPI(200, "保存成功");
    }


    /**
     * 更新书籍
     *
     * @param book
     * @return
     */
    @RequestMapping(path = "/books", method = RequestMethod.PATCH)
    public ResultAPI updBooks(@RequestBody Book book) {
        bookService.updBooks(book);
        return new ResultAPI(200, "更新成功");
    }


    /**
     * 删除书籍
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/books/{id}", method = RequestMethod.DELETE)
    public ResultAPI delBooks(@PathVariable(name = "id") Integer id) {
        bookService.deleteBooksByPrimaryKey(id);
        return new ResultAPI(200, "删除成功");
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> upload = qiniuConfig.upload(file);
        return upload;
    }




}
