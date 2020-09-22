package thread.t05_ALL_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 应森亮
 * @date 2020/09/01
 * @desc reentrantlock用于替代synchronized
 *   由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 *   这里是复习synchronized最原始的语义
 *
 *   使用reentrantlock可以完成同样的功能
 *   需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 *   使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放,除了这里还有哪里可以释放
 */
public class T14_ReentrantLock_lock_unlock {
    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();
            for(int i = 0; i< 2; i++){
                TimeUnit.SECONDS.sleep(2);
                System.out.println(i);
            }
            TimeUnit.SECONDS.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        System.out.println("XXX");
        lock.unlock();
    }

    public static void main(String[] args) {
        T14_ReentrantLock_lock_unlock reentrantLock = new T14_ReentrantLock_lock_unlock();
        new Thread(()->{
            reentrantLock.m1();
        },"Thread1").start();
        try{
            TimeUnit.SECONDS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(()->reentrantLock.m2()).start();
    }
}
