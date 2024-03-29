package com.eli.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyilin on 2019/3/17.
 */
public class HelloWorldCommand extends HystrixCommand<String> {

    private final String name;

    public HelloWorldCommand(String name) {
        // 最少配置：指定命令组名（CommandGroup）
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // 依赖逻辑封装在run()方法中
        return "Hello " + name + "thread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception{
        // 每个command对象只能调用一次，不可以重复调用。
        // 重复调用会报错：This instance can only be executed once. Please instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        // 使用execute()同步调用代码，效果等同于：HelloWorldCommand.queue().get()
        String result = helloWorldCommand.execute();
        System.out.println("result=" + result);

        helloWorldCommand = new HelloWorldCommand("Async-hystrix");
        // 异步调用，可自由控制获取结果时机
        Future<String> future = helloWorldCommand.queue();
        // get操作不能超过command定义的超时时间，默认：1s
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());
    }
}
