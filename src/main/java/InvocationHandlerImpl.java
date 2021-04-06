import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Desc TODO
 * @Author yugongxia
 * @Date 2021/4/6 15:18
 * @Version 1.0
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Object sum;

    public InvocationHandlerImpl(Object sum) {
        this.sum = sum;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getClassLoader());
        System.out.println(sum.getClass().getClassLoader());
        System.out.println("before");
        Object invoke = method.invoke(sum, args);
        System.out.println("end");
        return invoke;
    }
}
