package thread.t05_ALL_Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * desc: 栅栏
 * 满了以后 调用 Runnable  barrierAction
 * 本实例20一批20一批。
 * @author Ying
 * Date: 2020/9/2
 * @version 1.0.0
 */
public class T19_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("人满了，20个，" +
                "do  Runnable类型的 barrierAction"));

        for (int i = 0; i < 120; i++) {
            new Thread(() -> {
                try {
                    //每个线程都进行等待，等待栅栏放倒
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
