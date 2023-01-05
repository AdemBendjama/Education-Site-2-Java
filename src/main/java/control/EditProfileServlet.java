package control;

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

@WebServlet(name = "EditProfileServlet", value = "/editProfile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherEditProfile.jsp");
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

        if(session.getAttribute("teacher") != null){
            //
            User teacher = (User) session.getAttribute("teacher");
            String changeName = request.getParameter("changeName");

            //
            String name = subjectManager.editProfile(teacher.getEmail(),changeName);
            teacher.setUsername(name);

            //
            session.setAttribute("teacher",teacher);

            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);


        }else{
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);
        }

    }
}
