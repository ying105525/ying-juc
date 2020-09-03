package thread.t07_RefType;

/**
 * desc: XXXX
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class MForFinalize {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize方法执行，老子被回收了");
    }
}
