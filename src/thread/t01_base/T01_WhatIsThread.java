package thread.t01_base;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc  线程之间抢CPU时间片，并发跑。
 */
public class T01_WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                TimeUnit.MICROSECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 run()方法跑完了");
        }
    }

    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main 线程");
        }

    }
}
