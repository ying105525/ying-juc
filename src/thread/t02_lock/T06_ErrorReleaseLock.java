package thread.t02_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc
 */
public class T06_ErrorReleaseLock {
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " Start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 5){
                int i = 1/0;
                System.out.println("抛出异常了前面: " + i);
            }
        }
    }

    public static void main(String[] args) {
        T06_ErrorReleaseLock t = new T06_ErrorReleaseLock();
        new Thread(t::m,"T1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m,"T2").start();

    }
}
