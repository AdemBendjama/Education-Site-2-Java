package control;

import model.User;
import model.UserManager;

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
        if (session.getAttribute("student") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Student/StudentEditProfile.jsp");
            dispatcher.include(request, response);

        } else if (session.getAttribute("teacher") != null) {
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
        UserManager userManager = new UserManager();

        if(session.getAttribute("student") != null){
            //
            User student = (User) session.getAttribute("student");
            String changeName = request.getParameter("changeName");

            //
            String name = userManager.editProfile(student.getEmail(),changeName);
            student.setUsername(name);

            //
            session.setAttribute("student",student);

            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);


        }else if(session.getAttribute("teacher") != null){
            //
            User teacher = (User) session.getAttribute("teacher");
            String changeName = request.getParameter("changeName");

            //
            String name = userManager.editProfile(teacher.getEmail(),changeName);
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
