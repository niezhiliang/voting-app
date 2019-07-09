package com.suyu.voting.app.utils;

import java.util.Random;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 11:23
 */
public class JWTUtils {

    /**
     * 获取用户id
     * @return
     */
    public static Integer getUserId() {

        return new Random().nextInt(5);
    }

}
