package com.yunxi.demo.resilience4j;

import io.vavr.control.Try;

/**
 * @Author 无双
 * @Date 2018/12/14
 * @Description
 */
public class TestFallBack {
    public static void main(String[] args) {
        // 响应式编程 ---> Spring 5新特性 ——> spring姊妹篇 Reactor
        String result = Try.ofSupplier(() -> {
            int number = run();
            return "1 / 0 = " + number;
        }).recover(throwable -> "Fall Back 执行了").get();
        System.out.println(result);
    }

    public static int run() {
        return 1 / 0;
    }
}
