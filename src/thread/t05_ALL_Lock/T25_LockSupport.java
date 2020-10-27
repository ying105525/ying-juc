package thread.t05_ALL_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * desc: 锁支持 创建锁或其他同步化类的最基础的阻塞原语
 * Basic thread blocking primitives for creating locks and other
 * synchronization classes.
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T25_LockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
//                每隔一秒输出一个i
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-1");

//        LockSupport.unpark(t);// 在start之前不行
        t.start();
//            unpark 可以在park前调用
//        LockSupport.unpark(t);
//        8秒后 unpark() 线程继续执行
        try {
            TimeUnit.SECONDS.sleep(18);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 18 senconds!");

        LockSupport.unpark(t);

    }
}
