package lk.jiat.ee.timer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

@Stateless
public class TimerSessionBean {

    @Resource
    private TimerService timerService;

    public void doTask() {
        timerService.createTimer(1000,5000,"Clock");
    }

    @Timeout
    public void timeOutTask(){
        System.out.println("OK");
    }
}
