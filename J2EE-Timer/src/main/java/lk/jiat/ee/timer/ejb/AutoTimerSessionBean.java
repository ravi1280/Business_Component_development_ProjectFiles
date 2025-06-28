package lk.jiat.ee.timer.ejb;


import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import jakarta.ejb.Stateless;

@Stateless
public class AutoTimerSessionBean {

    @Schedules({
            @Schedule(hour = "*", minute = "30", second = "10", persistent = false),
            @Schedule(hour = "*", minute = "30", second = "10", persistent = false),
            @Schedule(hour = "*", minute = "30", second = "10", persistent = false),
            @Schedule(hour = "*", minute = "30", second = "10", persistent = false)
    })
    public void AutoSchedule(){
        System.out.println("Auto Schedule");
    }
}
