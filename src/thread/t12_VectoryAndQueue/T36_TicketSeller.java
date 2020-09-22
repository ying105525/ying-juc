package thread.t12_VectoryAndQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/04
 *
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 */
public class T36_TicketSeller {

//    线程不安全的List
//    static List<String> tickets = new ArrayList<>();

//    static {
//        for(int i=0; i<10000; i++) tickets.add("票编号：" + i);
//    }
//------------------------------------------------------------------

    static Vector<String> tickets = new Vector<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }
//   -----------------------------------------------------------------------

    public static void main(String[] args) {
//        10个线程都去卖票，判断票是不是售完，但是这里是线程不安全的。运行到最后会 java.lang.ArrayIndexOutOfBoundsException
//        for(int i=0; i<10; i++) {
//            new Thread(()->{
//                while(tickets.size() > 0) {
//                    System.out.println("销售了--" + tickets.remove(0));
//                }
//            }).start();
//        }

// * 使用Vector或者Collections.synchronizedXXX
// * 分析一下，这样能解决问题吗？

        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size() > 0) {
//					这里不是加锁的
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
