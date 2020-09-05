package thread.t11_HashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author 应森亮
 * @date 2020/09/03
 * @desc JDK中最好的容器，HashMap的祖宗，线程安全的，内部所有方法都是synchronized，
 * 效率不高，put还行，get很慢，内部就是Entry<K,V>
 */
public class T34_HashTable {
    public static final int COUNT = 1000000;
    public static final int THREAD_COUNT = 100;
    static Hashtable<UUID, UUID> m = new Hashtable<>();
    static UUID[] keys = new UUID[COUNT];
    static UUID[] values = new UUID[COUNT];

//    初始化一堆key 和 value 每个数组 100万 个
    static {
        for (int i = 0; i < COUNT; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = COUNT/THREAD_COUNT; //每个间隔 1万 个

        public MyThread(int start) {
            this.start = start;
        }

//        分段
        @Override
        public void run() {
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

//        100个线程
        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
//            每组1万个 KV 对
            threads[i] =
                    new MyThread(i * (COUNT/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("put的总时间"+ (end - start));

        System.out.println("Hashtable最后的大小" + m.size());

        //-----------------------------------

//        获取
        start = System.currentTimeMillis();
//        线程组里循环遍历，每个线程进行循环，0到100万，每次循环从Hashtable 中获取每一个值，为了体现HashTable get很慢
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    UUID uuid = m.get(keys[j]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("get花费的总时间" + (end - start));
    }
}
