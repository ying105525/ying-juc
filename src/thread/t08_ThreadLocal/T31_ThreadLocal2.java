package thread.t08_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/03
 * @desc
 */
public class T31_ThreadLocal2 {

    static java.lang.ThreadLocal<Person> tl = new java.lang.ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}
