package lk.jiat.ee.interceptor.interceptors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TestInterceptor {
    public void intercept() {
        System.out.println("TestIntercept called intercept method");
    }
}
