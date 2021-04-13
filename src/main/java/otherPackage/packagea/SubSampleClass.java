package otherPackage.packagea;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Desc TODO
 * @Author yugongxia
 * @Date 2021/4/12 18:34
 * @Version 1.0
 */
public class SubSampleClass {
    protected void test(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });

        SampleClass sample = (SampleClass) enhancer.create();
        sample.test();
    }
}
