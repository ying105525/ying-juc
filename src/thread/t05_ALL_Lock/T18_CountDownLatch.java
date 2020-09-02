package thread.t05_ALL_Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * desc: 门栓
 *  计数，到了指定的数量就放行。比如100，每此-1，wait()方法会判断到0，然后放心
 * @author Ying
 * Date: 2020/9/2
 * @version 1.0.0
 */
public class T18_CountDownLatch {

    private static void usingJoin() {
        Thread[] threads = new Thread[20];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始");
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" end Join");
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
//        100个计数的门栓
        CountDownLatch latch = new CountDownLatch(threads.length);

        for(int i = 0; i< threads.length; i++){
            threads[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                }
                System.out.println(result);
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
//            门栓插入，100
            System.out.println("等待----");
            latch.await();
            System.out.println("等待结束---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    public static void main(String[] args) {
        usingJoin();
//      usingCountDownLatch();
    }
}
