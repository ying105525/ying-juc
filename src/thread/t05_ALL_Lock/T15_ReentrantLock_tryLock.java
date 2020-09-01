package thread.t05_ALL_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 应森亮
 * @date 2020/09/01
 * @desc 使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class T15_ReentrantLock {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {

        boolean locked = false;

        try {
            System.out.println("m2 开始尝试加锁 :" + locked);
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 尝试加锁... :" + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T15_ReentrantLock reentrantLock = new T15_ReentrantLock();
        new Thread(reentrantLock::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(reentrantLock::m2).start();
    }
}
