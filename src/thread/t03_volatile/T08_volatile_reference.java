package thread.t03_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc volatile 引用类型（包括数组）只能保证 !引用本身! 的可见性，不能保证内部字段的可见性.
 */
public class T08_volatile_reference {
    boolean flag = true;

    public volatile static T08_volatile_reference t = new T08_volatile_reference();

    void m() {
        System.out.println("m start");
        while (flag) {
//            ,,,
        }
        System.out.println("m end");

    }

    void m2() {
        System.out.println("m2 start");
        while (t != null) {
//            ,,,
        }
        System.out.println("m2 end");

    }

    public static void main(String[] args) {
        new Thread(() -> {
            t.m();
        }, "Thread-1").start();

        new Thread(() -> {
            t.m2();
        }, "Thread-2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.flag = false;

        System.out.println("准备改变t 为 null");
        t = null;
    }
}

