package thread.t14_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc  ThreadPoolExecutor extends AbstractExecutorService，AbstractExecutorService 实现了 ExecutorService接口
 * ExecutorService接口 继承自 Executor
 */
public class T52_ThreadPoolExecutor {
    //静态类实现Runnable 接口 因为execute方法只能接受Runnable类型对象
    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }
    static class  MyThreadFactory implements ThreadFactory  {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final String name = "MyThreadPool-" +
                poolNumber.getAndIncrement() +
                "-thread-";;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,name);
        }
    }

//    自定义拒绝策略，本例子，如果是10个，最后2个未进入等待队列的就被抛弃了
    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r kafka mysql redis
            //try 3 times 什么时候有空了，丢回去
            if(executor.getQueue().size() < 10000) {
                System.out.println("这里是自定义的拒绝策略 aaaaaaaa");
                //try put again();
            }
        }
    }

    public static void main(String[] args) {
//        创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                // 这里入参是需要一个BlockingQueue，LinkedBlockingQueue, SynchronousQueue(来一个等一个)
                new ArrayBlockingQueue<>(4),
//                一般都会自定义ThreadFactory
//                Executors.defaultThreadFactory(),
                new MyThreadFactory(),
                //  线程池 所有线程都忙，任务队列满，执行拒绝策略，
                //  一般都是自定义，用来处理业务信息，比如订单啊，消息啊，都没有处理，要看看怎么处理。
//                new ThreadPoolExecutor.CallerRunsPolicy()
                new MyHandler()
        );

        for(int i = 0; i < 10; i++){
            threadPoolExecutor.execute(new Task(i));
        }
       //输出等待队列
        System.out.println("1-threadPoolExecutor的queue"+ threadPoolExecutor.getQueue());
//
//        队列也满了，这里就用拒绝策略了。当前是直接用在调用threadPoolExecutor的地方执行这个任务，此处是main线程调用threadPoolExecutor
//        A handler for rejected tasks that runs the rejected task
//        directly in the calling thread of the {@code execute} method,
        threadPoolExecutor.execute(new Task(100));

        System.out.println("2-threadPoolExecutor的queue"+threadPoolExecutor.getQueue());
//        threadPoolExecutor.execute(new Task(100));
        threadPoolExecutor.shutdown();
    }
}
