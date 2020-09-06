package thread.t14_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 应森亮
 * @date 2020/09/06
 * ForkJoinPool中每个线程有自己单独的任务队列，底层也是用了WorkStealing算法
 * 大任务切分成小任务 ，里面有RecursiveAction(不带返回值),RecursiveTask(有返回值)
 */
public class T57_ForkJoinPool {

//    一百万
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();
    static AtomicInteger num = new AtomicInteger(0);
//    初始化nums 数组
    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

//        输出和
        System.out.println("---" + Arrays.stream(nums).sum()); //stream api
    }


//    RecursiveAction 无返回值 abstract class RecursiveAction extends ForkJoinTask<Void>
    static class AddTask extends RecursiveAction {

        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected void compute() {

            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {

                int middle = start + (end-start)/2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }


        }

    }

//   RecursiveTask<>有返回值 abstract class RecursiveTask<V> extends ForkJoinTask<V>
    static class AddTaskRet extends RecursiveTask<Long> {

        private static final long serialVersionUID = 1L;
        int start, end;

        AddTaskRet(int s, int e) {
            start = s;
            end = e;
        }

        /**
         * 每次执行就会被调用，这里是递归调用的，最有右返回值
         * @return 此例子内部是递归
         */
        @Override
        protected Long compute() {
            //如果小于MAX_NUM就求和
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                return sum;
            }

            // 如果大于MAX_NUM就继续拆分
            int middle = start + (end-start)/2;

            // 递归求和
            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();
            System.out.println("当前递归层数"+ num.getAndIncrement() + "分段点为：" + middle);
            return subTask1.join() + subTask2.join();
        }

    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        从0开始，到1000000，只不过这里的1000000用nums.length 代替了
        AddTaskRet addTaskRet = new AddTaskRet(0,nums.length);
        forkJoinPool.execute(addTaskRet);
        Long join = addTaskRet.join();
        System.out.println(join);

//      AddTask 是 RecursiveAction 无返回值，并且是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
//      ForkJoinPool fjp = new ForkJoinPool();
//		AddTask task = new AddTask(0, nums.length);
//		fjp.execute(task);
//      System.in.read();

    }
}
