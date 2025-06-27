package lk.jiat.ee.timer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;

@Singleton
public class TimerSessionBean {

    @Resource
    private TimerService timerService;

//    Timer timer;

    public void doTask() {
//        timerService.createIntervalTimer(1000,5000,new TimerConfig());
//       timer = timerService.createTimer(60000,"Clock");
        timerService.createTimer(60000, "Clock");
    }

    @Timeout
    public void timeOutTask(Timer timer) {
        System.out.println("OK"+timer);
    }

    public void cancelTimer(){
//        if(timer != null){
//            timer.cancel();
//        }
    }
}
