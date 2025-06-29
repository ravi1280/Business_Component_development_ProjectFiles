package lk.jiat.ee.interceptor.interceptors;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.interceptor.annotation.Login;


@Interceptor
@Login
@Priority(1)
public class LoginInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        System.out.println(" login Start");
        return ctx.proceed();
    }
}
