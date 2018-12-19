package com.yunxi.demo.resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * @Author 无双
 * @Date 2018/12/14
 * @Description 流控
 */
public class TestRateLimiter {
    public static void main(String[] args) {
        // RateLimiter配置文件
        RateLimiterConfig config = RateLimiterConfig.custom()
                // 请求等待的超时时间
                .timeoutDuration(Duration.ofMillis(2000))
                // RateLimiter刷新频率
                .limitRefreshPeriod(Duration.ofMillis(200))
                // 并发是1
                .limitForPeriod(1)
                .build();
        // 创建一个RateLimiter
        RateLimiter rateLimiter = RateLimiter.of("secKill", config);

        // 函数式编程
        Supplier<String> restrictedSupplier = RateLimiter
                .decorateSupplier(rateLimiter, () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "true";
                });


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Try<String> aTry = Try.ofSupplier(restrictedSupplier);
                if (aTry.isSuccess()) {
                    System.out.println(aTry.get());
                } else {
                    System.out.println("false");
                }
            }).start();
        }

        // 5个并发
//        IntStream.rangeClosed(1,5)
//                .parallel()
//                .forEach(i -> {
//                    Try<String> aTry = Try.ofSupplier(restrictedSupplier);
//                    if (aTry.isSuccess()) {
//                        System.out.println(aTry.get());
//                    } else {
//                        System.out.println("对不起，秒杀失败，继续努力");
//                    }
//                });
    }
}
