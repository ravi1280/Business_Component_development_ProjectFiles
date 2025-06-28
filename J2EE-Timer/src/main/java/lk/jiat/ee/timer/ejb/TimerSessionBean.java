package lk.jiat.ee.timer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import lk.jiat.ee.timer.TimerPackage.Task;

import java.io.Serializable;
import java.util.UUID;

@Singleton

public class TimerSessionBean {

    @Resource
    private TimerService timerService;

    public Task doTask(long time) {

        TimerConfig timerConfig  =new TimerConfig();
        String taskId =UUID.randomUUID().toString();
        Task task = new Task(taskId,"Test Task");
        timerConfig.setInfo(task);
//        timerConfig.setPersistent(false);

        ScheduleExpression scheduleExpression = new ScheduleExpression();
        scheduleExpression.dayOfWeek("MON-FRI");
        scheduleExpression.dayOfMonth("MON");


        timerService.createCalendarTimer(scheduleExpression, timerConfig);
        return task;
    }

    @Timeout
    public void timeOutTask1(Timer timer) {
        Serializable info = timer.getInfo();
        if (info instanceof Task) {
            Task task = (Task) info;
            System.out.println(task.getTaskName() + " " + task.getTaskId() + "  Task01 Is Done");

        }
    }




    public void cancelTimer(String taskId) {

        //getAllTimers for get all beans timers
        //getTimers can get access in bean timers
        for (Timer timer : timerService.getAllTimers()) {
            if(timer.getInfo() instanceof Task && ((Task) timer.getInfo()).getTaskId().equals(taskId) ){
               timer.cancel();
                System.out.println(" Task Name : " +taskId+"  Task ID :" + taskId +" is cancelled");
               break;
            }
        }

    }
}
