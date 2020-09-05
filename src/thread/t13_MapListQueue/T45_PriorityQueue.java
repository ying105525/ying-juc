package thread.t13_MapListQueue;

import java.util.PriorityQueue;

/**
 * desc: 优先级队列，有排序的。
 *
 * @author Ying BG338501
 * Date: 2020/9/4
 * @version 1.0.0
 */
public class T45_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.add("a");
        queue.add("c");
        queue.add("e");
        queue.add("a");
        queue.add("d");
        queue.add("z");

//        排序后输出，a在最前面，最先输出。
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }
    }
}
