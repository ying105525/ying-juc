package thread.t13_MapListQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc: BlockingQueue ,阻塞队列，用LinkedBlockingQueue 实现去说明
 *
 * @author Ying BG338501
 * Date: 2020/9/4
 * @version 1.0.0
 */
public class T43_LinkedBlockingQueue {

//    LinkedBlockingDeque 这个也实现了BlockingQueue 接口，可以头尾操作。
// LinkedBlockingQueue 只能按照队列的操作来，一个个尾插

    static BlockingQueue<String> stringBlockingQueue = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    如果满了，就等待
                    stringBlockingQueue.put("--" + i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName() + " take -" + stringBlockingQueue.take()); //如果空了，就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread-" + i).start();
        }

    }

}
