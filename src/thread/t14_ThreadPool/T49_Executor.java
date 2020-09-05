package thread.t14_ThreadPool;

import java.util.concurrent.Executor;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc Executor接口,里面有个execute方法 表示执行
 */
public class T49_Executor implements Executor {

    public static void main(String[] args) {
        new T49_Executor().execute(()->{
            System.out.println("Executor 接口实现类对象调用execute 方法，入参是一个 Runnable 接口对象,当前线程为："
                    + Thread.currentThread().getName());
        });
    }

    @Override
    public void execute(Runnable command) {
        System.out.println("execute 方法" );
        command.run();
    }
}
