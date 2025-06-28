package lk.jiat.ee.interceptor.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.util.Map;

public class AInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        System.out.println("AInterceptor called intercept method");
        Map<String,Object> contextData =ic.getContextData();
        System.out.println("AInterceptor called intercept method" + contextData);
        ic.proceed();
        return null;
    }
}
