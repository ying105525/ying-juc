package thread.t10_Containers;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 应森亮
 * @date 2020/09/03
 * @desc
 */
public class T33_ArrayBlockingQueue {
    public static void main(String[] args) {

        Queue<Integer> q = new LinkedList();
        q.add(0);
        q.add(1);
//        放多个不会阻塞,上限好像是Integer.MAX
        q.add(2);
        q.add(3);
        System.out.println(q);

        List<Integer> list = new ArrayList();
        list.add(0);
        list.add(1);
//        放多个不会阻塞,上限好像是Integer.MAX
        list.add(2);
        list.add(3);
        System.out.println(list);


        Queue<Integer> q2 = new ArrayBlockingQueue<>(2);
        q2.add(0);
        q2.add(1);
//        放多个就阻塞，然后报错，不会自己扩容
        q2.add(2);
        q2.add(3);
        System.out.println(q2);

//        Collection
        Collection<Integer> c1 = new ArrayList();
        c1.add(1);
        c1.add(2);
        c1.add(3);
        c1.stream().forEach(System.out::println);

        List<Integer> c2 = new ArrayList<>(2);
        Set<Integer> c3 = new HashSet<>(2);
        List<Integer> c22 = new ArrayList<>();
        Set<Integer> c32 = new HashSet<>();
        Queue<Integer> c4 = new LinkedList<>();

    }
}
