package thread.t07_RefType;

import java.io.IOException;

/**
 * desc: 强引用。不管内存够不够，只有待回收的时候才会被回收，看GC的具体实现。
 *
 * @author Ying BG338501
 * Date: 2020/9/3
 * @version 1.0.0
 */
public class T26_NormalReference {
    public static void main(String[] args) throws IOException {
        MForFinalize m = new MForFinalize();
        m = null;
        System.gc(); // 一般这种显示的GC是禁止的。DisableExplicitGC

        System.in.read();
    }
}
