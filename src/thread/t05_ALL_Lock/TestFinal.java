package thread.t05_ALL_Lock;

/**
 * desc: XXXX
 *
 * @author Ying BG338501
 * Date: 2020/9/21
 * @version 1.0.0
 */
public class TestFinal {
    public static void main(String[] args) {
        final int min = getMin();
        System.out.println(min);
    }

    private static int getMin(){
        try{
            System.out.println("------------------");
            return 1;

        }finally {
            System.out.println("==========================");
//            return 2;  //输出变为了2
        }
    }
}
