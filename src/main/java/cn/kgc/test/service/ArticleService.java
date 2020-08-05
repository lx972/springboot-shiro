package cn.kgc.test.service;

import cn.kgc.test.model.Article;
import cn.kgc.test.utils.ResultAPI;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 9:56
 */
public interface ArticleService {

    /**
     * 添加文章到数据库
     *
     * @param article
     * @return
     */
    ResultAPI addArticle(Article article);
}
