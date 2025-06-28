package lk.jiat.ee.timer.servlert;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.timer.TimerPackage.Task;
import lk.jiat.ee.timer.ejb.TaskSessionBean;
import lk.jiat.ee.timer.ejb.TimerSessionBean;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@WebServlet("/test")
public class Test extends HttpServlet {
    @EJB
    private TaskSessionBean sessionBean;

    @EJB
    private TimerSessionBean timerBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//       Future<String> doTask =sessionBean.doTask();
//        try {
//           String s = doTask.get();
//           response.getWriter().println(s);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

       Task task = timerBean.doTask(10000);
        System.out.println(" Task Name : " +task.getTaskName()+" ,  Task ID :" + task.getTaskId() );
        request.getSession().setAttribute("task", task);
    }
}
