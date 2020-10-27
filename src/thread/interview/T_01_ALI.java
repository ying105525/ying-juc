package thread.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc: AtomicInteger
 *
 * @author Ying BG338501
 * Date: 2020/10/27
 * @version 1.0.0
 */
public class T_01_ALI {
    static AtomicInteger threadNo = new AtomicInteger(1);
    public static void main(String[] args) {
        new Thread(() -> {

                while (true) {
                    if(threadNo.get() == 1){
                        System.out.print("A");
                        threadNo.set(2);
                    }
                }

        }, "t1").start();
        new Thread(() -> {
            while (true) {
                if(threadNo.get() == 2){
                    System.out.print("L");
                    threadNo.set(3);
                }
            }

        }, "t2").start();
        new Thread(() -> {

            while (true) {
                if(threadNo.get() == 3){
                    System.out.print("I");
                    threadNo.set(1);
                }
            }

        }, "t3").start();
    }
}
