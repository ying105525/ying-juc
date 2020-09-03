package thread.t06_AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * desc: 自定义Sync 基础AQS
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T25_Sync extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
//         这里只有0 到 1 所以后面判断的isHeldExclusively 方法 只判断了1
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    public static void main(String[] args) {
        T25_Sync sync = new T25_Sync();
       Thread t1 =  new Thread(()->{
            System.out.println("线程1");
            sync.tryAcquire(1);
           final int state = sync.getState();
           System.out.println(state);
           System.out.println(Thread.currentThread().getName() + ":" +state);
       },"Thread-1");
//        Thread t2 =  new Thread(()->{
//            System.out.println("线程2");
//            System.out.println(sync.tryAcquire(1));
//            final int state = sync.getState();
//            System.out.println(Thread.currentThread().getName() + ":" +state);
//            System.out.println(state);
//        },"Thread-2");
       t1.start();
//       t2.start();
    }
}
