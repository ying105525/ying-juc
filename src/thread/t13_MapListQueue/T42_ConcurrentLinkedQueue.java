package thread.t13_MapListQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * desc: 不阻塞的
 *
 * @author Ying BG338501
 * Date: 2020/9/4
 * @version 1.0.0
 */
public class T42_ConcurrentLinkedQueue {
    public static void main(String[] args) {
//        ConcurrentLinkedQueue 也是一个	双端队列Deque
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for(int i=0; i<10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());

        //双端队列Deque
    }
}
