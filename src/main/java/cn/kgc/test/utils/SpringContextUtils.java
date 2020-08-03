package cn.kgc.test.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 10:17
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SpringContextUtils");
        SpringContextUtils.context=applicationContext;
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
