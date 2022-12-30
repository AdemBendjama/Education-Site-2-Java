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
import java.io.PrintWriter;
import java.io.Serial;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 102831973239L;

    public LoginServlet() {
        super();
    }
    //


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
            dispatcher.include(request, response);

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
            dispatcher = request.getRequestDispatcher("/WEB-INF/loginPage.jsp");
            dispatcher.include(request, response);

        }
    }

    //
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
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
                    dispatcher.forward(request, response);
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
                default -> {
                    // Wrong Login info result in a error msg and redirect to login page
                    PrintWriter writer = response.getWriter();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/loginPage.jsp");
                    dispatcher.include(request, response);
                    writer.print("""
                            <div><h3>Wrong Login Credentials !</h3></div>
                            <div class="footer">
                                <div>
                                    <p>© 2022 e-learning. All rights reserved.</p>
                                    <p>
                                        Université Constantine 2 Abdelhamid Mehri <br/>
                                        Nouvelle ville Ali Mendjeli BP : 67A, Constantine <br/>
                                        Algérie La Nouvelle Ville Ali Mendjeli, 25016
                                    </p>
                                </div>
                                <img src="resources/Logo-04-removebg-preview.png" alt="image of the logo of e-learning"/>
                                <div>
                                    <p>Phone :031 77 50 27</p>
                                    <p>Contact webmaster@univ-constantine2</p>
                                    <a href="WEB-INF/Admin/AdminMain.jsp">Admin</a>
                                    <a href="WEB-INF/Teacher/TeacherMain.jsp">Teacher</a>
                                    <a href="WEB-INF/Student/StudentMain.jsp">Student</a>
                                </div>
                            </div>""");
                }
            }


        }
    }
}
