package thread.t05_ALL_Lock;

import java.util.concurrent.Exchanger;

/**
 * desc: 交换2线程之间的值，只有一个exchange就阻塞等待了
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T24_Exchanger {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s  = "T1";
            try{
                s = exchanger.exchange(s);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " +s);
        },"t1").start();

        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();

        new Thread(()->{
            String s = "T3";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t3").start();
    }


}
