package thread.t05_ALL_Lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * desc: 阶段
 * <p>
 * * await()方法，可以使线程进入等待状态，在Phaser中，与之对应的方法是awaitAdvance(int n)。
 * * countDown()，使计数器减一，当计数器为0时所有等待的线程开始执行，在Phaser中，与之对应的方法是arrive()
 * <p>
 * Phaser替代CyclicBarrier比较简单，CyclicBarrier的await()方法可以直接用Phaser的arriveAndAwaitAdvance()方法替代
 * CyclicBarrier与Phaser:CyclicBarrier只适用于固定数量的参与者,而Phaser适用于可变数目的屏障.
 *
 * @author Ying
 * Date: 2020/9/2
 * @version 1.0.0
 */
public class T20_Phaser {

    static class MarriagePhaser extends Phaser {
//         这里的phase 相当于每一个阶段的标记，从0->1->2->3....
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了！");
//                    这里的返回值用于判断是否到了最后一个阶段
                    return false;
                case 1:
                    System.out.println("所有人都吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人离开了！");
                    System.out.println("婚礼结束了！");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
//            睡眠随机的时间
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }

    }

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
//        设置参与者为5个
        phaser.bulkRegister(5);

        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(()->{
                Person p = new Person("person "+ nameIndex);
                p.arrive();
//                等所有参与者都到了，进入下一行代码
                phaser.arriveAndAwaitAdvance();

                p.eat();
                phaser.arriveAndAwaitAdvance();

                p.leave();
                phaser.arriveAndAwaitAdvance();

            }).start();
        }
    }

}
