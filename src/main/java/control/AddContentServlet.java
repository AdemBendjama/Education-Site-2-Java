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

@WebServlet(name = "AddContentServlet", value = "/addContent")
public class AddContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/ModuleContent/TeacherAddContent.jsp");
            dispatcher.include(request, response);
        } else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        //
        String subjectName = request.getParameter("subject-name");
        String weekStart = request.getParameter("week-start");
        String weekEnd = request.getParameter("week-end");
        String type = request.getParameter("type");
        String contentLink = request.getParameter("contentLink");
        String contentName = request.getParameter("contentName");
        String description = request.getParameter("description");
        int weekID = subjectManager.getWeekID(subjectName, weekStart, weekEnd);
        if (session.getAttribute("teacher") != null
                && weekID != 0) {
            //
            subjectManager.changeDesc(weekID,description);

            //
            boolean added = false;
            switch (type) {
                case "cour" -> {
                    //
                    added = subjectManager.addCour(weekID, contentLink, contentName);

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
                    added = subjectManager.addTD(weekID, contentLink, contentName);
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
                    added = subjectManager.addTP(weekID, contentLink, contentName);

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
