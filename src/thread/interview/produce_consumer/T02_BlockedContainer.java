package thread.interview.produce_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc: XXXX
 *
 * @author Ying BG338501
 * Date: 2020/11/2
 * @version 1.0.0
 */
public class T02_BlockedContainer<T> {
    private static final int MAX_NUM = 10;
    private int count = 0;
    final private LinkedList<T> list = new LinkedList<>();
    private  Lock lock = new ReentrantLock();
    private  Condition producer = lock.newCondition();
    private  Condition consumer = lock.newCondition();

    private void put(T t){
        try{
            lock.lock();
            while(list.size() == MAX_NUM){
                producer.await();
            }
            list.add(t);
            count++;
            consumer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        try{
            lock.lock();
            while(list.size() == 0){
                consumer.await();
            }
            t = list.removeFirst();
            count --;
            producer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return  t;
    }

    public static void main(String[] args) {
        T02_BlockedContainer m = new T02_BlockedContainer();
        //consumer
        for(int i = 0; i< 10; i++){
            new Thread(()->{
                for(int j = 0; j<5;j++) System.out.println("consumer-" + j +"|| "+ m.get());
            },"C" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 2; i++){
            new Thread(()->{
                for(int j = 0;j < 25; j++){
                    m.put(Thread.currentThread().getName()+"-" + j);
                }
            },"producer-" + i).start();
        }
    }
}
