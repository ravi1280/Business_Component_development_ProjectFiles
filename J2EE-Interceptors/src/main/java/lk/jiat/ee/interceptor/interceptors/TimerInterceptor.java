package lk.jiat.ee.interceptor.interceptors;


import jakarta.annotation.Priority;
import jakarta.interceptor.AroundTimeout;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.interceptor.annotation.TimeOutLogger;

import java.util.Timer;

@TimeOutLogger
@Interceptor
@Priority(1)
public class TimerInterceptor {

    @AroundTimeout
    public Object aroundTimeOut(InvocationContext ctx) throws Exception {
        System.out.println("doAroundTimeout");

        Timer timer = (Timer) ctx.getTimer();
        timer.cancel();

        return ctx.proceed();
    }
}
