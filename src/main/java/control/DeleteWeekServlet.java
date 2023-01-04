package control;

import model.SubjectManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteWeekServlet", value = "/deleteWeek")
public class DeleteWeekServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if(session.getAttribute("teacher") != null){
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherDeleteWeek.jsp");
            dispatcher.include(request,response);
        }else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        if (session.getAttribute("teacher") != null) {
            //
            String subjectName = request.getParameter("subject-name");
            String weekStart = request.getParameter("week-start");
            String weekEnd = request.getParameter("week-end");

            //
            boolean deleted = subjectManager.deleteWeek(subjectName, weekStart, weekEnd);

            if (deleted) {
                //
                LoginServlet loginServlet = new LoginServlet();
                loginServlet.doGet(request, response);
            } else {
                //
                this.doGet(request, response);
            }


        } else {
            //
            this.doGet(request, response);
        }
    }
}
