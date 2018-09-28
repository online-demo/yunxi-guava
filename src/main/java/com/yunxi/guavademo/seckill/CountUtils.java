package com.yunxi.guavademo.seckill;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 无双老师
 * @Date 2018/9/28
 * @Description 计数器
 */
public class CountUtils {
    /**
     * 假设秒杀商品总数=200
     */
    public static final AtomicInteger TOTAL_COUNT = new AtomicInteger(10);

    /**
     * 秒杀成功，商品数量减1
     */
    public static int decrease() {
        return TOTAL_COUNT.decrementAndGet();
    }

}
