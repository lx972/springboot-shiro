package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.ArticleMapper;
import cn.kgc.test.model.Article;
import cn.kgc.test.service.ArticleService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 9:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 添加文章到数据库
     *
     * @param article
     * @return
     */
    @Override
    public ResultAPI addArticle(Article article) {
        article.setDate(new Date());
        articleMapper.insert(article);
        return new ResultAPI(200, "添加文章到数据库");
    }
}
