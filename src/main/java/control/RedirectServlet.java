package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
