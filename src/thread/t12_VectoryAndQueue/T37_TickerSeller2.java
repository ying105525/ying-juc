package thread.t12_VectoryAndQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 应森亮
 * @date 2020/09/04
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 */

public class T37_TickerSeller2 {
    static Vector<String> tickets = new Vector<>();

//vector的add是加锁的
    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size() > 0) {
//					这里不是加锁的
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

// remove方法是同步的，加了synchronized，但是由于方法没加锁，
// 所以还是会多个线程同时去调用remove方法，造成已经没有元素了但还是在remove。
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }

}
