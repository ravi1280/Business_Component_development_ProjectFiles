package lk.jiat.ee.timer.ejb;


import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Stateless
public class TaskSessionBean {

    @Resource
    ManagedExecutorService managedExecutorService;

    public Future<String> doTask(){
        System.out.println("doTask"+Thread.currentThread().getName());

       return managedExecutorService.submit(new Callable<String>() {
           @Override
            public String call() throws Exception {
                System.out.println("Sending Message"+Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);

                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("Done sending Message"+Thread.currentThread().getName());
                return "Done  Task";
            }
        });

    }

}
