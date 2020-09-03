package thread.t11_HashtableToCHM;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 应森亮
 * @date 2020/09/03
 * @desc HashMap, SynchronizedHashMap, ConcurrentHashMap
 * HashMap 无锁，线程不安全 1.7 1.8区别等等
 * SynchronizedHashMap 加锁版的HashMap，比Hashtable稍微好一点，锁了一个类内部的final Object(final Object      mutex;
 * // Object on which to synchronize) ,锁的粒度小一点。
 * CHM： 因为currenthashmap将数据分段，分成一段一段的，每一段都加锁，锁分离技术，访问的时候，
 * * 访问第一段数据，第一段加锁，第二段没有锁，其他线程还是可以访问第二段的数据的，所以比自己hashmap加锁的效率要高
 */
public class T35_HashMap {
    public static final int COUNT = 1000000;
    public static final int THREAD_COUNT = 100;
    static Map<UUID, UUID> m = new ConcurrentHashMap<>();

    static UUID[] keys = new UUID[COUNT];
    static UUID[] values = new UUID[COUNT];

    static {
        for (int i = 0; i < COUNT; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = COUNT / THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new MyThread(i * (COUNT / THREAD_COUNT));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("put所花费的时间为：" + (end - start));

        System.out.println("CHM最后的大小为： " + m.size());

        //-----------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("get所花时间为： " + (end - start));
    }
}
