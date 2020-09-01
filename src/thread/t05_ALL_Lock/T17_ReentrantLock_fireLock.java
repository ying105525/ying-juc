package thread.t05_ALL_Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 应森亮
 * @date 2020/09/01
 * @desc  ReentrantLock还可以指定为公平锁,公平锁会先检查等待队列里有没有线程等待
 */
public class T17_ReentrantLock_fireLock extends Thread{

    private static ReentrantLock lock=new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果,公平会有交替输出

//    交替获得锁
    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        T17_ReentrantLock_fireLock rl=new T17_ReentrantLock_fireLock();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}
