package com.suyu.voting.app.quartz;


import com.suyu.voting.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 17:10
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class JobInvokeTest {

    @Autowired
    private QuartzService quartzService;

    @Test
    public void testQuartz() throws SchedulerException, InterruptedException {

        //quartzService.addJob(JobInvoke.class,1,new Date(System.currentTimeMillis() + 1000 * 5));

        Thread.sleep(10000);
    }

}
