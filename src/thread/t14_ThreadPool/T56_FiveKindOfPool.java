package thread.t14_ThreadPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc  Executors-线程池的工厂
 * 五种线程池  SingleThreadPool、CachedPool、FixedThreadPool、ScheduledPool、WorkStealingPool
 */
public class T56_FiveKindOfPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
//
//        /**
//         *   1.    SingleThreadPool 只有一个线程
//         *   new FinalizableDelegatedExecutorService
//         *             (new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()));
//         */
//        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
//        for(int i=0; i<5; i++) {
//            final int j = i;
//            singleThreadPool.execute(()->{
//                System.out.println(j + " " + Thread.currentThread().getName());
//            });
//        }
//
//
//        System.out.println("-------------------------------------");
//
//        /**
//         *  2. CachedPool
//         * //     * Creates a thread pool that creates new threads as needed, but
//         * //     * will reuse previously constructed threads when they are
//         * //     * available.  These pools will typically improve the performance
//         * //     * of programs that execute many short-lived asynchronous tasks.
//         *  return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
//         */
//        ExecutorService cachedPool = Executors.newCachedThreadPool();
//        System.out.println(cachedPool);
//        for (int i = 0; i < 2; i++) {
//            cachedPool.execute(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName());
//            });
//        }
//        System.out.println(cachedPool);
//
//        TimeUnit.SECONDS.sleep(80);
//
//        System.out.println(cachedPool);
//
//        /**
//         * FixedThreadPool
//         *  return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>());
//         *
//         */
//
//        System.out.println("-------------------------------------");
//        long start = System.currentTimeMillis();
//        getPrime(1, 200000);
//        long end = System.currentTimeMillis();
//        System.out.println("最原始的方法" + (end - start));
//
////        CPU数量
//        final int cpuCoreNum = 4;
//
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(cpuCoreNum);
//
//        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
//        MyTask t2 = new MyTask(80001, 130000);
//        MyTask t3 = new MyTask(130001, 170000);
//        MyTask t4 = new MyTask(170001, 200000);
//
//        Future<List<Integer>> f1 = fixedThreadPool.submit(t1);
//        Future<List<Integer>> f2 = fixedThreadPool.submit(t2);
//        Future<List<Integer>> f3 = fixedThreadPool.submit(t3);
//        Future<List<Integer>> f4 = fixedThreadPool.submit(t4);
//
//        start = System.currentTimeMillis();
//        f1.get();
//        f2.get();
//        f3.get();
//        f4.get();
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
//
        /**
         * ScheduledPool
         * new ScheduledThreadPoolExecutor(corePoolSize);
         *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *               new DelayedWorkQueue());
         *  Creates a thread pool that can schedule commands to run after a
         *  given delay, or to execute periodically.
         */
        System.out.println("---------------------------------------------------");
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
        /**
         *      * Creates and executes a periodic action that becomes enabled first
         *      * after the given initial delay, and subsequently with the given
         *      * period; that is executions will commence after
         *      * {@code initialDelay} then {@code initialDelay+period}, then
         *      * {@code initialDelay + 2 * period}, and so on.
         *      * If any execution of the task
         *      * encounters an exception, subsequent executions are suppressed.
         *      * Otherwise, the task will only terminate via cancellation or
         *      * termination of the executor.  If any execution of this task
         *      * takes longer than its period, then subsequent executions
         *      * may start late, but will not concurrently execute.
         */
        scheduledPool.scheduleAtFixedRate(()->{
            try {
                System.out.println("进入任务输出");
                TimeUnit.MILLISECONDS.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
        // 循环任务，以上一次任务的结束时间计算下一次任务的开始时间
        scheduledPool.scheduleWithFixedDelay(()->{
            try {
                System.out.println("XXXXXXX");
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0,1000,TimeUnit.MILLISECONDS);


//        /**
//         * WorkStealingPool
//         * 分叉组合的线程池，每个线程有自己单独的任务队列，当自己的线程完成自己的任务队列中的人物后会去其他线程的任务队列偷
//         * 内部实现new了一个 ForkJoinPool
//         * 这个类的目的是为了提供一个更方便的接口
//         * new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
//         *   ForkJoinPool.defaultForkJoinWorkerThreadFactory,null, true);
//         *
//         */
//        System.out.println("---------------------------------------------------");
//        ExecutorService workStealingPool = Executors.newWorkStealingPool();
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        workStealingPool.execute(new R(1000));
//        workStealingPool.execute(new R(2000));
//        workStealingPool.execute(new R(2000));
//        workStealingPool.execute(new R(2000)); //daemon
//        workStealingPool.execute(new R(2000));
//        workStealingPool.execute(new R(2000));
//        workStealingPool.execute(new R(2000));
//        workStealingPool.execute(new R(2000)); //daemon
//        workStealingPool.execute(new R(2000));
//
//        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
//        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time  + " " + Thread.currentThread().getName());

        }

    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

    }

    /**
     * 判断是否为质数
     * @param num
     * @return
     */
    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    /**
     * 返回这个区间内的质数
     * @param start
     * @param end
     * @return
     */
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            if(isPrime(i)) results.add(i);
        }

        return results;
    }


}
