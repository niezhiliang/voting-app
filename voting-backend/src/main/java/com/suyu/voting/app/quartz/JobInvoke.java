package com.suyu.voting.app.quartz;

import com.suyu.voting.app.entity.Theme;
import com.suyu.voting.app.entity.User;
import com.suyu.voting.app.service.IThemeService;
import com.suyu.voting.app.service.IUserService;
import com.suyu.voting.app.utils.SpringContextUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 17:08
 */
@Data
@Slf4j
public class JobInvoke extends QuartzJobBean implements Job {

    private Integer id;

    private Boolean isOpen;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

        IThemeService themeService = SpringContextUtil.getBean(IThemeService.class);

        boolean flag = themeService.openTheme(this.id,this.isOpen);
        if (flag) {
            Theme theme = themeService.getById(id);

            IUserService iUserService = SpringContextUtil.getBean(IUserService.class);

            User user = iUserService.getById(theme.getUserid());
            if (this.isOpen) {
                log.info("由「 {} 」发起的投票【{}】已经开启,id:{}",user.getNickName(),theme.getTitle(),this.id);
            } else {
                log.info("由「 {} 」发起的投票【{}】已经完成,id:{}",user.getNickName(),theme.getTitle(),this.id);
            }
        } else {
            log.error("id为:{}启动失败",this.id);
        }

    }
}
