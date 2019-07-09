package com.suyu.voting.app.quartz;

import com.suyu.voting.app.utils.UUIDUitls;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 17:02
 */
@Component
public class QuartzService {
    @Resource
    private Scheduler scheduler;

    public void addJob(Class clazz,Integer id,boolean isOpen, Date startAt) throws SchedulerException {
        // 启动调度器
        scheduler.start();

        String jobName = UUIDUitls.uuidNoSplit();
        String groupId = UUIDUitls.uuidNoSplit();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName,groupId)
                .usingJobData("id",id)
                .usingJobData("isOpen",isOpen)
                .build();

        //创建触发器 只执行一次
        SimpleTrigger simpleTrigger =  (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(jobName, groupId)
                .startAt(startAt)
                .forJob(jobName, groupId)
                .build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }

    public void deleteJob(String jobName,String groupId) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupId));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupId));
        scheduler.deleteJob(JobKey.jobKey(jobName, groupId));
    }

    public void stopJob(String jobName, String groupId) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, groupId));
    }

    public void resumeJob(String jobName, String groupId) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, groupId));
    }
}
