package thread.t08_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/03
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 */
public class T31_ThreadLocal {

    static class Person {
        String name = "zhangsan";
    }
    static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

//     搞2个线程，修改同一个对象。期待结果是每个线程保持自己独有的对象
    public static void main(String[] args) {
        new Thread(()->{
            try{
                final ThreadLocal<Person> personThreadLocal = ThreadLocal.withInitial(() -> new Person());
                System.out.println("=============" + personThreadLocal);
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(threadLocal.get());

        },"Thread-1").start();

//        先睡一秒，由于上方线程睡2S，所以这里的修改会在上面的打印之前进行
        new Thread(()->{
          try{
              TimeUnit.SECONDS.sleep(1);
          }catch (InterruptedException e){
              e.printStackTrace();
          }
          threadLocal.set(new Person());
            System.out.println("Thread 2 操作完成");
        }).start();
    }

}
