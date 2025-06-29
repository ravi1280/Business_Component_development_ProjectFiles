package lk.jiat.ee.interceptor.ejb;


import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import lk.jiat.ee.interceptor.annotation.TimeOutLogger;

@Stateless
@TimeOutLogger
public class TimerSessionBean {

    @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
    @Timeout
    public void doTask() {
        System.out.println(" doTask Start ...");
    }
}
