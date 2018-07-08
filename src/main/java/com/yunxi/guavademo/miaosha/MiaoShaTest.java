package com.yunxi.guavademo.miaosha;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * 秒杀场景模拟
 */
public class MiaoShaTest {

    public static void main(String[] args) throws InterruptedException {

        //限流，每秒允许10个请求进入秒杀
        RateLimiter limiter = RateLimiter.create(10);

        for (int i = 0; i < 100; i++) {
            //100个线程同时抢购
            new Thread(() -> {
                //每个秒杀请求如果100ms以内得到令牌，就算是秒杀成功，否则就返回秒杀失败
                if (limiter.tryAcquire(50, TimeUnit.MILLISECONDS)) {
                    System.out.println("恭喜您，秒杀成功");
                } else {
                    System.out.println("秒杀失败，请继续努力");
                }
            }).start();
            //等待新的令牌生成
            Thread.sleep(10);
        }
    }
}
