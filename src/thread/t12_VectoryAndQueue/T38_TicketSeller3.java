package thread.t12_VectoryAndQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *
 * @author Ying BG338501
 * Date: 2020/9/4
 * @version 1.0.0
 */
public class T38_TicketSeller3 {

//     这里用了List ,然后实现是LinkedList 不是线程安全的。
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
//					判断size和进行remove必须是一整个的原子操作
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;

                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
