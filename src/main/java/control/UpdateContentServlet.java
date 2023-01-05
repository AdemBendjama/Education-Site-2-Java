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

@WebServlet(name = "UpdateContentServlet", value = "/updateContent")
public class UpdateContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/ModuleContent/TeacherUpdateContent.jsp");
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
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        //
        String subjectName = request.getParameter("subject-name");
        String weekStart = request.getParameter("week-start");
        String weekEnd = request.getParameter("week-end");
        String type = request.getParameter("type");
        String contentLink = request.getParameter("contentLink");
        String newLink = request.getParameter("newLink");
        String newName = request.getParameter("newName");
        String description = request.getParameter("description");

        int weekID = subjectManager.getWeekID(subjectName, weekStart, weekEnd);

        if (session.getAttribute("teacher") != null
                && weekID != 0) {
            //
            subjectManager.changeDesc(weekID, description);

            //
            boolean added;
            switch (type) {
                case "cour" -> {
                    //
                    added = subjectManager.updateCour(weekID, contentLink, newLink, newName);

                    if (added) {
                        //
                        LoginServlet loginServlet = new LoginServlet();
                        loginServlet.doGet(request, response);
                    } else {
                        this.doGet(request, response);
                    }

                }
                case "td" -> {
                    //
                    added = subjectManager.updateTD(weekID, contentLink, newLink, newName);
                    if (added) {
                        //
                        LoginServlet loginServlet = new LoginServlet();
                        loginServlet.doGet(request, response);
                    } else {
                        this.doGet(request, response);
                    }
                }
                case "tp" -> {
                    //
                    added = subjectManager.updateTP(weekID, contentLink, newLink, newName);

                    if (added) {
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
