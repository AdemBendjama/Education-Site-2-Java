package control;

import model.UserManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "RedirectServlet", value = "/redirect")
public class RedirectServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 102831978573239L;

    public RedirectServlet() {
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
            userListServlet.doGet(request, response);

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
}
