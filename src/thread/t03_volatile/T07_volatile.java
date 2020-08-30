package thread.t03_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必(不一定，并不是一定不知道)知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * <p>
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * <p>
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * <p>
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * <p>
 * MSI 、缓存一致性
 * 保证线程可见性，禁止指令重排序 DCL单例，Double check Lock Mgr06.java
 */
public class T07_volatile {
    /**
     * 对比加不加volatile的区别
     */
     boolean runningFlag = true;

    public void m() {
        System.out.println("m start");
        while (runningFlag) {
            //   never stop until runningFlag is false
        }
        System.out.println("runningFlag is false, m end");
    }

    public static void main(String[] args) {
        T07_volatile t1 = new T07_volatile();
        new Thread(t1::m,"Thread1").start();

        // 这里睡一秒是为了让出时间片，让之前的线程获得时间片,保证之前的线程先于下面的输出代码执行
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("准备改变 runningFlag的值");
        t1.runningFlag = false;

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
