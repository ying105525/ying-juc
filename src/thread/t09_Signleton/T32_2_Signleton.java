package thread.t09_Signleton;

import thread.t06_AQS.T25_Sync;

/**
 * desc: XXXX
 *
 * @author Ying BG338501
 * Date: 2020/9/24
 * @version 1.0.0
 */
public class T32_2_Signleton {
    private T32_2_Signleton(){
        System.out.println("初始化一下");
    }
    private static class Inner{
        private static  T32_2_Signleton instance =  new T32_2_Signleton();
    }
    public T32_2_Signleton getInstance(){
        return Inner.instance;
    }

}
