package thread.interview.produce_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc: XXXX
 *  * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @author Ying BG338501
 * Date: 2020/11/2
 * @version 1.0.0
 */
public class T01_Container<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    //	2个条件 Condition 本质是等待队列
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while(lists.size() == MAX) { //想想为什么用while而不是用if？
                System.out.println("----------------------");
                producer.await();//到producer 这个等待队列里等待
            }

            lists.add(t);
            ++count;
            consumer.signalAll(); //通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count --;
            producer.signalAll(); //通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T01_Container<String> c = new T01_Container<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
