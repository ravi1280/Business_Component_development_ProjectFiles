package lk.jiat.ee.timer.servlert;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.timer.TimerPackage.Task;
import lk.jiat.ee.timer.ejb.TimerSessionBean;

import java.io.IOException;

@WebServlet("/test2")
public class Test2 extends HttpServlet {
    @EJB
    private TimerSessionBean timerBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       if(request.getSession().getAttribute("task")!= null){
          Task task = (Task) request.getSession().getAttribute("task");
        timerBean.cancelTimer(task.getTaskId());
       }
    }
}
