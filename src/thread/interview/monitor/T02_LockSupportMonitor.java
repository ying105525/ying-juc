package thread.interview.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * desc: XXXX
 *  * 实现一个容器，提供两个方法，add，size
 *  * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * @author Ying BG338501
 * Date: 2020/11/2
 * @version 1.0.0
 */
public class T02_LockSupportMonitor {
    // 添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        T02_LockSupportMonitor c = new T02_LockSupportMonitor();

        t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            //System.out.println("t2启动");
            //if (c.size() != 5) {

            LockSupport.park();

            //}
            System.out.println("t2 结束");
            LockSupport.unpark(t1);


        }, "t2");

        t2.start();
        t1.start();

    }
}
