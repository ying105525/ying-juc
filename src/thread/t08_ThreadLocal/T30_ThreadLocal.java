package thread.t08_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * desc: 没有属于自己线程独有的变量，这里输出的是被其他线程修改后的名称
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T30_ThreadLocal {
    volatile static Person p = new Person();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//			没有属于自己线程独有的变量，这里输出的是被其他线程修改后的名称
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}
