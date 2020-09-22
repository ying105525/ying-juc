package thread.t05_ALL_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 应森亮
 * @date 2020/09/01
 * 使用ReentrantLock还可以调用lockInterruptibly方法，
 * 可以对线程interrupt方法做出响应， 在一个线程等待锁的过程中，可以被打断
 *
 */
public class T16_ReentrantLock_lockInterruptibly {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("AAAinterrupted!");
            } finally {
                lock.unlock();
            }
        });

        t1.start();

        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2 尝试获取锁");
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("我被打断了");
                e.printStackTrace();
            }finally {
                System.out.println(":::" + Thread.currentThread().getName());
                if(((ReentrantLock) lock).isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        },"Thread2");
        t2.start();

        System.out.println("准备打断t2");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("进行打断t2");
        t2.interrupt();
    }


}
