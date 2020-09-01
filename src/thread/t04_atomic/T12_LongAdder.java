package thread.t04_atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * desc: LongAdder的使用，内部也是使用了Unsafe的compareAndSwapXXX
 *
 * @author Ying
 * Date: 2020/9/1
 * @version 1.0.0
 */
public class T12_LongAdder {
    static LongAdder count = new LongAdder();

    public static void main(String[] args) {

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.increment();
                }
            }, "Thread-" + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("over now! count is : " + count);
    }
}
