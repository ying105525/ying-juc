package thread.t09_Signleton;

import java.util.Arrays;

/**
 * desc: XXXX
 *
 * @author Ying BG338501
 * Date: 2020/10/14
 * @version 1.0.0
 */
public class TestSingleton {

    private TestSingleton() {
        System.out.println("初始化方法执行...");
    }

    private static class InnerClass {
        private static TestSingleton testSingleton = new TestSingleton();
    }

    public static TestSingleton getInstance() {
        return InnerClass.testSingleton;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        System.out.println("XXXXX");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(TestSingleton.getInstance());
            });
        }
        Arrays.asList(threads).forEach(thread -> thread.start());
    }
}
