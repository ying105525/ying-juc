package thread.t14_ThreadPool;

import java.util.concurrent.*;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc
 *  Callable接口，对Runnable进行了扩展
 *  对Callable的调用，可以有返回值
 */
public class T51_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hello, I'v sleep 1S";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> result = service.submit(c);
        System.out.println("主线程输出，接下来是获取异步的call的返回结果");
//        这里用shutdown会等线程池里的任务运行完
        service.shutdown();
        System.out.println(result.get());//阻塞
        System.out.println("XXXX");

//        service.shutdown();
    }
}
