package thread.t02_lock;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc 对某个对象 或 Class 上锁
 * Synchronized 实现原理： 对象头部2位标识加锁状态。无锁-》偏向锁-》自旋锁=》重量级锁。（可能是这样的00,01,10,11）
 */
public class T04_Synchronized implements Runnable {

    private static int count2 = 10;
    private Object o = new Object();
    private int count = 10;


    public void m() {
//        任何线程要执行下面这段代码，都必须先拿到o的锁
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count= " + count);
        }
    }

    public void m2() {
        synchronized (this) {
            count--;
            System.out.println("给this加锁" + Thread.currentThread().getName() + "count= " + count);
        }
    }

    //    等同于在方法的代码执行时要synchronized(this)
    public synchronized void m3() {
        count--;
        System.out.println("synchronized 方法" + Thread.currentThread().getName() + "count= " + count);
    }

    /**
     * 这里等同于synchronized(T.class)
     * 锁的是T这个Class类的对象，注意是Class类的对象，表示T这个Class。 就是T.class
     */
    public synchronized static void m4() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            count2--;
            System.out.println("synchronized 静态方法" + Thread.currentThread().getName() + "count2= " + count2);
//            Thread.sleep(1000);
        }
    }

    public static void m5() {
        System.out.println("这里是m5方法---1");
//        synchronized(T04_Synchronized.class) { //考虑一下这里写synchronized(this)是否可以？ 不可以，直接报错了，没有对象
//            System.out.println("这里是m5方法---2");
//            count2 --;
//        }
    }

//    public static void m6() {
//        synchronized(this) { //考虑一下这里写synchronized(this)是否可以？
//            count2 --;
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        T04_Synchronized t = new T04_Synchronized();
        for (int i = 0; i < 10; i++) {
            new Thread(t, "Thread" + i).start();
        }
        T04_Synchronized.m4();
        T04_Synchronized.m5();
    }


    @Override
    public void run() {
        m();
    }
}
