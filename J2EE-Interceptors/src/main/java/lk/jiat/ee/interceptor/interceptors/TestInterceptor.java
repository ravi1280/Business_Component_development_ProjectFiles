package lk.jiat.ee.interceptor.interceptors;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.interceptor.AroundConstruct;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


public class TestInterceptor {
    @AroundConstruct
    public void aroundConstruct(InvocationContext ctx)  {
        System.out.println("TestConstructor - AroundConstruct : "+ctx.getConstructor());
    }

    @PostConstruct
    public void init(InvocationContext ic) {
        System.out.println("TestInterceptor init");
        Constructor<?> constructor= ic.getConstructor();
        System.out.println("TestIntercept constructor : " + constructor);
    }
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        System.out.println("TestIntercept Start");

        System.out.println("TestIntercept called intercept method");

         //we can get which method is being invoked
        Method method = ic.getMethod();
        System.out.println("TestIntercept called intercept method : " + method);

       // modify parameters before invoking the method
        Object[] parameters = ic.getParameters();
        System.out.println(Arrays.toString(parameters));
        ic.setParameters(new Object[]{"Indraji"});

        //we can get  sessionBean Object
        Object target = ic.getTarget();
        System.out.println("TestIntercept  target : " + target);

         //we can get Context Data and Add
        Map<String, Object> contextData = ic.getContextData();
        System.out.println("TestIntercept contextData : " + contextData);
        contextData.put("Age", "25");

       Object proceed = ic.proceed();
        System.out.println("TestIntercept proceedObject : " + proceed);
        System.out.println("TestIntercept End");
        return proceed;
    }

    @PreDestroy
    public void destroy(InvocationContext ic) {
        System.out.println("TestInterceptor destroy");
    }
}
