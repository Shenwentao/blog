package cn.xiaqileyu.blog.handler;

import cn.xiaqileyu.blog.mapper.BlogMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * XxlJob开发示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、在Spring Bean实例中，开发Job方法，方式格式要求为 "public ReturnT<String> execute(String param)"
 * 2、为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author swt
 * @date 2020/6/24
 */
@Component
public class JobHandler {

    @Resource
    private BlogMapper blogMapper;

    /**
     * 清理被删除时间大于30天的博客文章
     *
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("blogRecycleHandler")
    public ReturnT<String> blogRecycleHandler(String param) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        try {
            blogMapper.deleteRecycleBlog(c.getTime());
        } catch (Exception e) {
            XxlJobLogger.log("XXL-JOB：blogRecycleHandler : message: {}", e.getMessage());
        }
        return ReturnT.SUCCESS;
    }
}