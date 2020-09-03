package thread.t07_RefType;

import java.lang.ref.WeakReference;

/**
 * desc: 弱引用遭到gc就会回收
 * 作用：当强引用消失，就不用管这个了。一般用到容器
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T28_WeakReference {
    public static void main(String[] args) {
        WeakReference<MForFinalize> m = new WeakReference<>(new MForFinalize());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


//        ThreadLocal<MForFinalize> tl = new ThreadLocal<>();
//        tl.set(new MForFinalize());
////        使用ThreadLocal务必要remove
//        tl.remove();

    }
}
