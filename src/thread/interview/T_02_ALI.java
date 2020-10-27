package thread.interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * desc: LockSupport
 *
 * @author Ying BG338501
 * Date: 2020/10/27
 * @version 1.0.0
 */
public class T_02_ALI {
    static Thread t1 = null, t2 = null, t3 = null;
    public static void main(String[] args) {
        t1 = new Thread(()->{
            while(true){
                System.out.print("A");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(()->{
            while(true){
                LockSupport.park();
                System.out.print("l");
//                LockSupport.park();
                LockSupport.unpark(t3);
            }
        });

        t3 = new Thread(()->{
            while(true){
                LockSupport.park();
                System.out.print("i_");
//                LockSupport.park();
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
