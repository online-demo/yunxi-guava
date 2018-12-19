package com.yunxi.demo.guava.smoothbursty;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author 无双老师
 * @Date 2018/9/28
 * @Description 平滑突发限流(SmoothBursty) 使用场景：对冷数据的预热处理
 */
public class SmoothBurstyRateLimitTest02 {

    public static void main(String[] args) {
        //每秒允许5个请求，表示桶容量为5且每秒新增5个令牌，即每隔0.2秒新增一个令牌
        RateLimiter limiter = RateLimiter.create(5);
        //一次性消费5个令牌，模拟预热的场景（初始化redis缓存）
        System.out.println(limiter.acquire(5));
        //limiter.acquire(1)将等待差不多1秒桶中才能有令牌
        System.out.println(limiter.acquire(1));
        //固定速率
        System.out.println(limiter.acquire(1));
        //固定速率
        System.out.println(limiter.acquire(1));
        //固定速率
        System.out.println(limiter.acquire(1));
    }
}
