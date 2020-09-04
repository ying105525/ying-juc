package thread.t12_VectoryAndQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用ConcurrentQueue提高并发性
 *
 * @author Ying BG338501
 * Date: 2020/9/4
 * @version 1.0.0
 */
public class T39_TicketSeller4 {
    //	内部是使用CAS的
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++) {
            new Thread(()->{
//                while(true) {
//                    String s = tickets.poll();
//                    if(s == null) break;
//                    else System.out.println("销售了--" + s);
//                }
                while(tickets.size()>0) {
//                    人为睡眠，导致最后多个线程都通过了判断，来到了这里。所以后面还要判断一下 s 是不是为空
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String s = tickets.poll();
                    if(s == null) break;
                    System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
