package thread.t05_ALL_Lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/01
 * @desc reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 */
public class T13_ReviewSynchronized {
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i为："+ i);
        }
    }
    synchronized void m2(){
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        T13_ReviewSynchronized t1 = new T13_ReviewSynchronized();
        new Thread(()->{
            t1.m1();
        },"Thread1").start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(()->{
            t1.m2();
        },"Thread2").start();
    }
}
