package com.yunxi.demo.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;

import java.util.function.Supplier;

/**
 * @Author zhouguanya
 * @Date 2018/12/14
 * @Description
 */
public class TestRetry {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
        // 默认重试三次
        Retry retry = Retry.ofDefaults("TestRetry");

        // 熔断器包装请求
        Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, () -> run());

        // 包装自从重试机制
        decoratedSupplier = Retry
                .decorateSupplier(retry, decoratedSupplier);

        // 包装降级逻辑
        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Fall back 执行了").get();
        System.out.println(result);
    }

    public static String run() {
        throw new RuntimeException("异常了");
    }
}
