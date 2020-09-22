package thread.t13_MapListQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc 延迟队列，按时间进行任务调度
 *
 *  * An unbounded {@linkplain BlockingQueue blocking queue} of
 *  * {@code Delayed} elements, in which an element can only be taken
 *  * when its delay has expired.
 *
 *  DelayQueue<E extends Delayed>  元素需要实现Delayed接口
 *
 */
public class T46_DelayQueue {

    static class MyTask implements Delayed{

        String name;
        long runningTime;

        MyTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
           if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
            return -1;
			else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }


    static BlockingQueue<MyTask> tasks = new DelayQueue<>();




    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for(int i=0; i<5; i++) {
            System.out.println(tasks.take());
        }
    }

}
