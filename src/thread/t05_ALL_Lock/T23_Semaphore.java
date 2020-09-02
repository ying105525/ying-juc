package thread.t05_ALL_Lock;

import java.util.concurrent.Semaphore;

/**
 * desc: 信号量
 * 功能：限流。允许多少个线程同时执行
 * 读作：[ˈseməfɔː(r)]
 * 举例：车道和收费站，8车道，2个收费站
 * @author Ying BG338501
 * Date: 2020/9/2
 * @version 1.0.0
 */
public class T23_Semaphore {

    public static void main(String[] args) {
        //允许一个线程同时执行
//        Semaphore s = new Semaphore(1);

//                Semaphore s = new Semaphore(2);

        //        公平锁，交替输出
        Semaphore s = new Semaphore(2, true);

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T1 获得了许可");
                Thread.sleep(1000);
                System.out.println("T1 睡眠 1S 后输出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                需要手动释放
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T2 获得了许可");
                Thread.sleep(1000);
                System.out.println("T2 睡眠 1S 后输出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                需要手动释放
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T3 获得了许可");
                Thread.sleep(1000);
                System.out.println("T3 睡眠 1S 后输出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                需要手动释放
                s.release();
            }
        }).start();
    }
}
