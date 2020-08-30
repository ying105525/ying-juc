package thread.t01_base;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc 创建线程的三种方法
 * 1 继承Thread类, 2 实现Runnable，3 创建线程池 Executors.newCachedThreadPool() or ThreadPoolExecutor
 */
public class T02_HowToCreateThread {
    /**
     * 继承Thread类
     */
    static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " :这里是T1 类对象");
        }
    }

    static class Run1 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " :这里是Run1 类对象");
        }
    }

    public static void main(String[] args) {
//        直接new 自定义线程然后start
        new T1().start();
        // 把自定义 线程 交给Thread类 然后start
        new Thread(new T1(),"第一种实现方法").start();
        new Thread(new Run1(),"第二种实现方法").start();
        new Thread(()->{
            System.out.println("第二种实现方法的lamda表达式写法");
        },"landa写法").start();

    }

}
