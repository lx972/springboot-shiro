package cn.kgc.test.service.impl;

import cn.kgc.test.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 11:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {
}
