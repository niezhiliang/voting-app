package com.suyu.voting.app.exception;

import com.alibaba.fastjson.JSONObject;
import com.suyu.voting.app.common.ResultCode;
import com.suyu.voting.app.common.ResultVO;
import lombok.Getter;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 15:57
 */
public class VoteException extends RuntimeException {

    @Getter
    private String response;

    public VoteException() {
    }


    public VoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoteException(ResultCode resultCode) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultCode.getCode());
        resultVO.setMsg(resultCode.getMsg());
        this.response = JSONObject.toJSONString(resultVO);
    }

}
