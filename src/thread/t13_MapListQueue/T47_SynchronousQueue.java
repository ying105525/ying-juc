package thread.t13_MapListQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc 同步queue
 * 作用： 给另外一个线程下达任务
 * 容量为0不能往里装，只能put，相当于递过去
 */
public class T47_SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
//   takeL  * Retrieves and removes the head of this queue, waiting if necessary
//          * for another thread to insert it.
                System.out.println(queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        System.out.println("传递了aaa");
        queue.put("aaa"); //没人消费就阻塞等待消费者消费
        //strs.put("bbb");
        //strs.add("aaa"); //容量为0不能往里装，只能put，相当于递过去
        System.out.println(queue.size());
    }
}
