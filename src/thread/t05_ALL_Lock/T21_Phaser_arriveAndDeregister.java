package thread.t05_ALL_Lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * desc: 阶段
 * arriveAndDeregister 表示达到然后解除寄存
 * 这个例子主要用于展示动态调整每个阶段的实际参与者， 每个阶段需要达到的人数还是不变的，由 phaser.bulkRegister(7) 指定。
 *
 * @author Ying
 * Date: 2020/9/2
 * @version 1.0.0
 */
public class T21_Phaser_arriveAndDeregister {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static class MarriagePhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("第一阶段完成！ 一共有" + registeredParties + "个参与者");
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("第二阶段完成！ 一共有" + registeredParties + "个参与者");
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("第三阶段完成！ 一共有" + registeredParties + "个参与者");
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("第四阶段完成！ 一共有" + registeredParties + "个参与者");
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Runnable {

        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
//            到达并且等待其他人到达
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房！\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
//                到达并且解除寄存
                phaser.arriveAndDeregister();
                //phaser.register()
            }
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();

        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);

//        5个客人
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("player" + i)).start();
        }

//        2个主角
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }
}
