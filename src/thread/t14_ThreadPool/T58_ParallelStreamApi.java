package thread.t14_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 应森亮
 * @date 2020/09/06
 * 流式API，流式处理
 * 流到我这里，我就处理
 */
public class T58_ParallelStreamApi {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
//        初始化数组
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }

        //System.out.println(nums);

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println("暴力循环所用时间为："+ (end - start));

        //使用parallel stream api

        start = System.currentTimeMillis();
//		并行流，里面用的也是ForkJoinPool,不需要同步的时候会快一点
        nums.parallelStream().forEach(T58_ParallelStreamApi::isPrime);
        end = System.currentTimeMillis();

        System.out.println("使用并行流式求解" + (end - start));
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
