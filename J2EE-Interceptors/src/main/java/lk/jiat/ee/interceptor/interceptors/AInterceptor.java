package lk.jiat.ee.interceptor.interceptors;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.interceptor.annotation.Login;

import java.util.Map;

@Interceptor
@Login
@Priority(2)
public class AInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        System.out.println("AIntercept Start");

        System.out.println("AInterceptor called intercept method");
        Map<String,Object> contextData =ic.getContextData();
        System.out.println("AInterceptor called intercept method" + contextData);

        Object proceed = ic.proceed();
        System.out.println("AIntercept proceed : " + proceed);

        System.out.println("AIntercept End");
        return proceed;
    }
}
