package lk.jiat.ee.interceptor.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Method;
import java.util.Arrays;


public class TestInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        System.out.println("TestIntercept called intercept method");

        // we can get which method is being invoked
        Method method = ic.getMethod();
        System.out.println("TestIntercept called intercept method" + method);

        //modify parameters before invoking the method
        Object[] parameters = ic.getParameters();
        System.out.println(Arrays.toString(parameters));

        ic.setParameters(new Object[]{"Indraji"});


        ic.proceed();
        return null;
    }
}
