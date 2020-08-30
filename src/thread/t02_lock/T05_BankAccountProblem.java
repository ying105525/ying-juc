package thread.t02_lock;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/08/30
 * @desc 银行账户不加锁脏读问题
 *
 */
public class T05_BankAccountProblem {
    String name;
    BigDecimal balance;

    /**
     * 这里睡 2S 是为了后面读取的时候产生脏读
     * 这里的synchronized相当于synchronized(this) 给自身对象加锁
     * @param name
     * @param balance
     */
    public synchronized void set(String name, BigDecimal balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ BigDecimal getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        T05_BankAccountProblem account = new T05_BankAccountProblem();
        new Thread(() -> {
            account.set("A", BigDecimal.valueOf(100));
        }, "T1").start();

        //        只睡1秒，由于还没有赋值完，所以拿不到100
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("A"));


        //        又睡2秒，睡晚已经赋值完了，所以可以拿到
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));
    }
}
