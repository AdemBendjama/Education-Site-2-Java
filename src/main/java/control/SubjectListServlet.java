package control;

import model.Subject;
import model.SubjectManager;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(name = "SubjectListServlet", value = "/listSubjects")
public class SubjectListServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 1680462654L;

    public SubjectListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        SubjectManager subjectManager = new SubjectManager();

        if (session.getAttribute("teacher") != null) {
            //
            User teacher = (User) session.getAttribute("teacher");
            List<Subject> listOfSubjects = subjectManager.getTeacherSubjects(teacher.getEmail());
            session.setAttribute("listOfSubjects", listOfSubjects);

            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherMain.jsp");
            dispatcher.forward(request, response);

        } else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

}
