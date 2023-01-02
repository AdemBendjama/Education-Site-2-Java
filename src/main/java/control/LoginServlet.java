package control;

import model.AdminManager;
import model.LoginAuthenticator;
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

        if (session.getAttribute("admin") != null) {
            //
            session.setAttribute("session", "admin");

            UserListServlet userListServlet = new UserListServlet();
            userListServlet.doGet(request, response);

        } else if (session.getAttribute("teacher") != null) {
            //
            session.setAttribute("session", "teacher");

            SubjectListServlet subjectListServlet = new SubjectListServlet();
            subjectListServlet.doGet(request, response);


        } else if (session.getAttribute("student") != null) {
            //
            session.setAttribute("session", "student");

            dispatcher = request.getRequestDispatcher("/WEB-INF/Student/StudentMain.jsp");
            dispatcher.include(request, response);

        } else {
            //
            session.setAttribute("session", null);

            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);

        }
    }

    //
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        HttpSession session = request.getSession();
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


            switch (accountPrivilege) {
                case "admin" -> {
                    // Admin Account
                    session.setAttribute("admin", adminManager.getAdmin(email));
                    session.setAttribute("session", "admin");
                    session.setAttribute("optionSelected", "email");

                    // Forwards to admin homepage
                    UserListServlet userListServlet = new UserListServlet();
                    userListServlet.doGet(request, response);
                }
                case "teacher" -> {
                    // Teacher Account
                    session.setAttribute("teacher", userManager.getUser(email));
                    session.setAttribute("session", "teacher");

                    // Forwards to teacher homepage
                    SubjectListServlet subjectListServlet = new SubjectListServlet();
                    subjectListServlet.doGet(request, response);
                }
                case "student" -> {
                    // Student Account
                    session.setAttribute("student", userManager.getUser(email));
                    session.setAttribute("session", "student");

                    // Forwards to student homepage
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Student/StudentMain.jsp");
                    dispatcher.forward(request, response);
                }

            }

        } else {
            // Wrong Login info result in a error msg and redirect to login page
            session.setAttribute("session", null);

            dispatcher = request.getRequestDispatcher("/WEB-INF/IncorrectLoginInfo.jsp");
            dispatcher.include(request, response);
        }
        // lol
    }
}

