package thread.t03_volatile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc
 */
public class T09_volatileVsSynchronized {
    /*volatile*/ int count = 0;

    synchronized void m() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++)
            count++;
    }

    public static void main(String[] args) {
        T09_volatileVsSynchronized t = new T09_volatileVsSynchronized();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }

}
