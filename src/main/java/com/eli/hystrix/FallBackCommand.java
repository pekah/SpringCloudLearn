package com.eli.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyilin on 2019/3/17.
 * 除了HystrixBadRequestException异常之外，所有从run()方法抛出的异常都算作失败，并触发降级getFallback()和断路器逻辑。

 HystrixBadRequestException用在非法参数或非系统故障异常等不应触发回退逻辑的场景。
 */
public class FallBackCommand extends HystrixCommand<String> {

    private final String name;

    public FallBackCommand(String name) {
        // 最少配置：指定命令组名（CommandGroup）
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                //配置依赖超时时间，500ms
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // 执行时间超过设定的500ms，超时后会调用fallback方法
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello " + name + "thread:" + Thread.currentThread().getName();
    }


    @Override
    protected String getFallback() {
        return "executed fall";
    }

    public static void main(String[] args) throws Exception{
        FallBackCommand helloWorldCommand = new FallBackCommand("test-fallback");
        String result = helloWorldCommand.execute();
        System.out.println("result=" + result);
    }
}
