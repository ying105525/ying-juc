package thread.t13_MapListQueue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author 应森亮
 * @date 2020/09/05
 * @desc  传递queue
 *   传递的内容是有长度的，一般等一个结果才能继续往下走的情况。如果是transfer 需要先消费者等着，在transfer之后take会阻塞；
 *   put不会，可以先put在take。
 */
public class T48_TransferQueue {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        /**
         * 装完在这里等着，等人来把取走，再继续干自己的事情，不满也在那里等着。需要先消费者等着。
         */
        strs.transfer("aaa");

//        这里阻塞，因为前面没有线程继续take了。用put不会阻塞，后面的take可以获取到。
        strs.transfer("aaa2");
//        System.out.println("阻塞了吗");

//        strs.put("aaa2");
		new Thread(() -> {
			try {
                System.out.println("XXX");
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
//        strs.put("aaa2");
        System.out.println("阻塞了吗");

    }
}
