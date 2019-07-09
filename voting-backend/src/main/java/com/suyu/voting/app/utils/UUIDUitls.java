package com.suyu.voting.app.utils;

import java.util.UUID;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 19:24
 */
public class UUIDUitls {

    /**
     * 获取待斜杠的uuid
     * @return
     */
    public static String uuidWithSplit() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取去斜杠的uuid
     * @return
     */
    public static String uuidNoSplit() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
