package control;

import model.SubjectManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddWeekServlet", value = "/addWeek")
public class AddWeekServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherAddWeek.jsp");
            dispatcher.include(request, response);
        } else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        if (session.getAttribute("teacher") != null) {
            //
            String subjectName = request.getParameter("subject-name");
            String weekStart = request.getParameter("week-start");
            String weekEnd = request.getParameter("week-end");

            //
            boolean added = subjectManager.addWeek(subjectName, weekStart, weekEnd);

            if (added) {
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
