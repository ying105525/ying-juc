package thread.t04_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc: 多次执行输出结果不同
 *
 * @author Ying
 * Date: 2020/9/1
 * @version 1.0.0
 */
public class T11_AtomicNotProvideParalle {
    static volatile boolean flag = true;

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger();

        new Thread(()->{
            while (flag){
                a.incrementAndGet();
            }
        }).start();

        new Thread(()->{
            int a1 = a.incrementAndGet();
            int a2 = a.incrementAndGet();
            System.out.println(a2-a1);
//            为了让前面那个子线程停止
            flag = false;
        }).start();
    }
}
