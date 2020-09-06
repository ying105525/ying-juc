package thread.t15_FasleSharing;

/**
 * @author 应森亮
 * @date 2020/09/06
 * @desc 缓存行共享问题
 */
public class T59_CacheLinePadding {

    private static class T {
        public volatile long x = 0L;
    }
//    定义数组
    public static T[] arr = new T[2];

//    初始化数组
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

//  占满缓存行
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }
    private static class T2 extends Padding {
        public volatile long x = 0L;
    }

    public static T2[] arr2 = new T2[2];

    static {
        arr2[0] = new T2();
        arr2[1] = new T2();
    }

    public static void main(String[] args) throws Exception {

//        给数组第一个元素的成员变量赋值，从0开始赋值到 1000_0000L
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
//        输出所花时间
        System.out.println((System.nanoTime() - start)/100_0000);


        Thread t3 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr2[0].x = i;
            }
        });

        Thread t4 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr2[1].x = i;
            }
        });

        final long start2 = System.nanoTime();
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("加了7个long以后" +(System.nanoTime() - start2)/100_0000);
    }
}
