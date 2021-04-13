import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @Desc 俩数之和
 * 无序数组 hash查找
 * 有序数组 二分查找 双指针 前后指针
 * BST 先序遍历树 双指针
 * @Author yugongxia
 * @Date 2021/4/6 11:16
 * @Version 1.0
 */
public class TwoSum implements Sum{

    Object proxyedField;


    @Override
    public int[] twoSum(int[] num, int target) {
        if (num == null || num.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < num.length; i++) {
            int x = num[i];

            // 哈希查找
            if (map.containsKey(target - x)) {
                int index = map.get(target -x);
                return new int[]{index, i};
            }
            map.put(num[i], i);
        }
        return new int[0];
    }

    static class myClassLoad extends ClassLoader {
//        private String path;
//        public myClassLoad(String classPath){
//            path=classPath;
//        }

        /**
         * 重写findClass方法
         * @param name 是我们这个类的全路径
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
           return null;
        }

    }

    public static void main(String[] args) {
        Thread.currentThread().setContextClassLoader(new myClassLoad());
        TwoSum twoSum = new TwoSum();
        final int[] num = new int[] {1,2,4,3,8};
//        int[] ints = twoSum.twoSum(num, 7);
//        twoSum.proxyedField = Proxy.newProxyInstance(twoSum.getClass().getClassLoader(), twoSum.getClass().getInterfaces(), new InvocationHandlerImpl(twoSum));

        Sum o = (Sum) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), twoSum.getClass().getInterfaces(), new InvocationHandlerImpl(twoSum));

        int[] ints = o.twoSum(num, 7);
        for (int anInt : ints) {
            System.out.println(anInt);
        }


    }
}
