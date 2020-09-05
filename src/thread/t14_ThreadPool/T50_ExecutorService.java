package thread.t14_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc  ExecutorService 接口，继承自Executor接口，多了一些方法。比如submit
 *  * 认识 ExecutorService,阅读API文档
 *  * 认识submit方法，扩展了execute方法，具有一个返回值
 *  * submit相当于异步的，我丢给你就不管了
 */
public class T50_ExecutorService {

    public static void main(String[] args) {
//        Executors 此处先不管，可以理解为一个创建线程池的工厂
        ExecutorService executorService = Executors.newCachedThreadPool();
//        submit是异步的，独立线程。submit入参可以使Runnable也可以是Callable
        executorService.submit(()->{
            System.out.println("老子是submit方法的入参对象，当前线程为：" + Thread.currentThread().getName());
        });
        executorService.shutdown();
    }
}
