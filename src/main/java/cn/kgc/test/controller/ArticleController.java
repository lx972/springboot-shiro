package cn.kgc.test.controller;

import cn.kgc.test.model.Article;
import cn.kgc.test.service.ArticleService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 9:58
 */

@RestController
@RequestMapping(path = "/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章到数据库
     *
     * @return
     */
    @RequestMapping(path = "/jotter/article", method = RequestMethod.POST)
    public ResultAPI addArticle(@RequestBody Article article) {
        try {
            return articleService.addArticle(article);
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }
}
