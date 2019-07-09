package com.suyu.voting.app.service;

import com.suyu.voting.app.entity.Members;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suyu.voting.app.vos.FansVO;

import java.util.List;

/**
 * <p>
 * 投票候选人表 服务类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
public interface IMembersService extends IService<Members> {

    /**
     * 获取某候选人的支持者
     * @param id
     * @return
     */
    List<FansVO> getFans(Integer id);

}
