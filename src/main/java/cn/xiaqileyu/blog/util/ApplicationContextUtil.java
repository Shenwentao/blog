package cn.xiaqileyu.blog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取Bean工具类
 *
 * @author swt
 * @date 2020/6/24
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取实例对象
     *
     * @param beanName 类名等
     * @return 实例对象
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}
