package thread.t04_atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 应森亮
 * @date 2020/08/31
 * @desc 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的（+ - 是原子的，但线程不安全，看WWW文件）
 * 凡是用Atomic开头的，都是用CAS来保证原子性的，号称无所
 * CAS操作来实现的 并不是sync加锁的
 */
public class T10_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);

    void m() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
        System.out.println("XXXX");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public static void main(String[] args) {
        T10_AtomicInteger t = new T10_AtomicInteger();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"Thread-"+i));
        }

        threads.forEach(r->r.start());
//        下面用jion去等待每一个线程都执行完。注意，各子线程之前已经开始执行了，并不理会下面的join。
//        下面的join影响只是等第一个子线程执行完以后再去join第二个子线程，也就是第二个+++一定是第一个子线程执行完之后才会打印的
        threads.forEach(r->{
            try {
                System.out.println("+++++++++");
                r.join();
                System.out.println("=======");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
