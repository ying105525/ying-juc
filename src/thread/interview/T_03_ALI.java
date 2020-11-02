package thread.interview;

/**
 * desc: CAS
 *
 * @author Ying BG338501
 * Date: 2020/10/27
 * @version 1.0.0
 */
public class T_03_ALI {

    enum ReadyThread {T1, T2, T3}

    static volatile ReadyThread r = ReadyThread.T1;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (r == ReadyThread.T1) {
                    System.out.print("A");
                    r = ReadyThread.T2;
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (r == ReadyThread.T2) {
                    System.out.print("L");
                    r = ReadyThread.T3;
                }
            }
        }, "t2").start();

        new Thread(() -> {
            while (true) {
                if (r == ReadyThread.T3) {
                    System.out.print("i");
                    r = ReadyThread.T1;
                }
            }
        }, "t3").start();

    }
}
