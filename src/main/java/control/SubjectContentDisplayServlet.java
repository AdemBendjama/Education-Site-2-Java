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
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "SubjectContentDisplayServlet", value = "/subject")
public class SubjectContentDisplayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        if (session.getAttribute("subjectName") != null
                && session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherModuleDisplay.jsp");
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

        //
        if (session.getAttribute("teacher") != null) {
            //
            String subjectName = request.getParameter("subject-name");
            HashMap<Integer,String> weeks = subjectManager.getWeeks(subjectName);

            //
            List<Integer> weekIDs = subjectManager.getWeekIDs(subjectName);
            HashMap<Integer,HashMap<String,String>> cours = subjectManager.getCours(weekIDs);
            HashMap<Integer,HashMap<String,String>> tds = subjectManager.getTDs(weekIDs);
            HashMap<Integer,HashMap<String,String>> tps = subjectManager.getTPs(weekIDs);
            HashMap<Integer,String> descriptions = subjectManager.getDescs(weekIDs);

            session.setAttribute("subjectName", subjectName);
            session.setAttribute("weeks", weeks);
            session.setAttribute("cours", cours);
            session.setAttribute("tds", tds);
            session.setAttribute("tps", tps);
            session.setAttribute("descriptions", descriptions);

            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherModuleDisplay.jsp");
            dispatcher.forward(request, response);

        } else {
            //
            this.doGet(request, response);
        }


    }
}
