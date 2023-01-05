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

@WebServlet(name = "DeleteContentServlet", value = "/deleteContent")
public class DeleteContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/ModuleContent/TeacherDeleteContent.jsp");
            dispatcher.include(request, response);
        } else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        //
        String subjectName = request.getParameter("subject-name");
        String weekStart = request.getParameter("week-start");
        String weekEnd = request.getParameter("week-end");
        String type = request.getParameter("type");
        String contentLink = request.getParameter("contentLink");

        int weekID = subjectManager.getWeekID(subjectName, weekStart, weekEnd);

        if (session.getAttribute("teacher") != null
                && weekID != 0) {

            //
            boolean deleted;
            switch (type) {
                case "cour" -> {
                    //
                    deleted = subjectManager.deleteCour(weekID, contentLink);

                    if (deleted) {
                        //
                        LoginServlet loginServlet = new LoginServlet();
                        loginServlet.doGet(request, response);
                    } else {
                        this.doGet(request, response);
                    }

                }
                case "td" -> {
                    //
                    deleted = subjectManager.deleteTD(weekID, contentLink);
                    if (deleted) {
                        //
                        LoginServlet loginServlet = new LoginServlet();
                        loginServlet.doGet(request, response);
                    } else {
                        this.doGet(request, response);
                    }
                }
                case "tp" -> {
                    //
                    deleted = subjectManager.deleteTP(weekID, contentLink);

                    if (deleted) {
                        //
                        LoginServlet loginServlet = new LoginServlet();
                        loginServlet.doGet(request, response);
                    } else {
                        this.doGet(request, response);
                    }
                }
            }
        } else {
            this.doGet(request, response);
        }
    }
}
