package thread.t09_Signleton;

import java.util.Arrays;

/**
 * @author 应森亮
 * @date 2020/09/03
 * @desc 静态内部类实现线程安全并且懒加载的单例
 * 类装载时，静态内部类不会被装载。调用公共方法时，加载内部类，加载只会有一次，所以线程安全。【在类进行初始化时，别的线程是无法进入的。 】
 */
public class T32_Singleton {
    private T32_Singleton(){
        System.out.println("singleton");
    }

//    静态内部类，内部喊一个私有的成员静态变量
    private static class Inner{
        private static T32_Singleton singleton = new T32_Singleton();
    }

    public static T32_Singleton getInstance(){
        return Inner.singleton;
    }

    public static void main(String[] args) {
        Thread[ ] threads = new Thread[20];
        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                System.out.println(T32_Singleton.getInstance());
            });
        }
        Arrays.asList(threads).forEach(o->o.start());
    }
}
