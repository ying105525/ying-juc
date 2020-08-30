package thread.t01_base;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc Thread类的sleep方法，yield()方法以及对象的join()方法
 * Sleep : 是简单的线程睡眠。   public static native void sleep(long millis) throws InterruptedException;
 * yield() : 从CPU上先离开，先让出一下，返回就绪状态。进入等待队列再等调度进行调用，不管后面其他线程能不能抢到;
 * join() : 将其他线程加入进来，等加进来的运行完再运行(sleep再久也没用)，一般用来按顺序
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {

//        System.out.println("开始测试Sleep方法");
//        testSleep();

//        System.out.println("开始测试Yield方法");
//        testYield();

        System.out.println("开始测试Join方法");
        testJoin();

    }

    static class T1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
//                System.out.println(Thread.currentThread().getName() + ": 目前到了 " + i + " 次");
                if (i % 10 == 0) {
//                    让出CPU 时间片
                    Thread.yield();
                }
            }
        }
    }


    static void testJoin(){
        Thread t1 = new Thread(()->{
            System.out.println("T1 开始了");
            for(int i =0; i<10; i++){
                System.out.println("---t1---: A" + i);
                try{
                    Thread.sleep(1000);
//                    Thread.interrupted();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            System.out.println("T2 开始了,准备让T1 join");
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 正式开始循环了");
            for(int i =0; i<10; i++){
                System.out.println("t2: A" + i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

    static void testYield() {
//        new Thread(new T1(), "Thread-T1").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": 目前到了 " + i + " 次");
                if (i % 2 == 0) {
                    Thread.yield();
                }
            }
        }, "T1 ").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("-------" + Thread.currentThread().getName() + ": 目前到了 " + i + " 次");
                if (i % 2 == 0) {
                    System.out.println("T2 循环内部");
//                    Thread.yield();
                }
            }
        }, "Thrad-T2").start();
    }

    protected static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " ：Sleep 一下");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "测试Sleep").start();
    }

}
