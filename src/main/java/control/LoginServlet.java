package control;

import model.AdminManager;
import model.LoginAuthenticator;
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
import java.io.Serial;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 102831588973239L;

    public LoginServlet() {
        super();
    }
    //
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();

        if (session.getAttribute("admin") != null) {
            //
            UserListServlet userListServlet = new UserListServlet();
            userListServlet.doGet(request,response);

        } else if (session.getAttribute("teacher") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherMain.jsp");
            dispatcher.include(request, response);

        } else if (session.getAttribute("student") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Student/StudentMain.jsp");
            dispatcher.include(request, response);

        } else {
            //
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);

        }
    }

    //
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        LoginAuthenticator loginAuthenticator = new LoginAuthenticator();
        AdminManager adminManager = new AdminManager();
        UserManager userManager = new UserManager();

        //
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        //
        String accountPrivilege = loginAuthenticator.authenticate(email, password);

        if (accountPrivilege != null) {
            //
            HttpSession session = request.getSession();

            switch (accountPrivilege) {
                case "admin" -> {
                    // Admin Account
                    session.setAttribute("admin", adminManager.getAdmin(email));

                    // Forwards to admin homepage
                    UserListServlet userListServlet = new UserListServlet();
                    userListServlet.doGet(request,response);
                }
                case "teacher" -> {
                    // Teacher Account
                    session.setAttribute("teacher", userManager.getUser(email));

                    // Forwards to teacher homepage
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherMain.jsp");
                    dispatcher.forward(request, response);
                }
                case "student" -> {
                    // Student Account
                    session.setAttribute("student", userManager.getUser(email));

                    // Forwards to student homepage
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Student/StudentMain.jsp");
                    dispatcher.forward(request, response);
                }

            }

        } else {
            // Wrong Login info result in a error msg and redirect to login page
            dispatcher = request.getRequestDispatcher("/WEB-INF/IncorrectLoginInfo.jsp");
            dispatcher.include(request, response);
        }
        // lol
    }
}

