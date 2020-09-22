package thread.t14_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc  future表示线程执行完的结果，注意，是异步的
 * 异步
 *
 *  FutureTask 即是Future也是task（Runnable），执行完以后的结果也存在了这个对象里
 *  实现了接口 RunnableFuture ，RunnableFuture 接口 extends Runnable, Future<V>
 *
 */
public class T54_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        睡1秒后返回整数1000
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });

        new Thread(task,"Thread-1").start();

        System.out.println("等待结果返回");
//        用get阻塞,Future的方法，等待返回结果
        System.out.println(task.get());
    }
}
