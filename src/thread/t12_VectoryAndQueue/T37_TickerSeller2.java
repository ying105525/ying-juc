package thread.t12_VectoryAndQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/04
 * @desc
 */
public class T37_TickerSeller2 {
    static List<String> tickets = new LinkedList<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(true) {
//					判断size和进行remove必须是一整个的原子操作
                    synchronized(tickets) {
                        if(tickets.size() <= 0) break;

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
