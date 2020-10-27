package thread.t14_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc 待完成后继续，可以判断是否所有的Future都返回了。
 *  *  假设你能够提供一个服务
 *  * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 */
public class T55_CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start, end;

        start = System.currentTimeMillis();

//        priceOfTM();
//        priceOfTB();
//        priceOfJD();
//
//        end = System.currentTimeMillis();
//        System.out.println("use serial method call! " + (end - start));

//        异步执行
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(()->2.0 );

//        所有的都开始并且等待结果返回。Returns the result value when complete, or throws an
//       (unchecked) exception if completed exceptionally.
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

//        演示连续的操作
        CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str-> "price " + str)
                .thenAccept(System.out::println);


        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM() {
        delay();
        System.out.println("获取到了TM的价格");
        System.out.println("\n");
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        System.out.println("获取到了TB的价格");
        System.out.println("\n");
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        System.out.println("获取到了JD的价格");
        System.out.println("\n");
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    /**
     * 模拟获取信息所花时间
     */
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
