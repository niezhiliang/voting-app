package com.suyu.voting.app.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 15:06
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 程序中途异常处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = VoteException.class)
    @ResponseBody
    public String ProcessExceptionHandler(VoteException e) throws Exception {
        return e.getResponse();
    }
}
