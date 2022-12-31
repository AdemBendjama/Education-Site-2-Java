package control;

import model.UserManager;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(name = "UserListServlet", value = "/listUsers")
public class UserListServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 10283984239L;

    public UserListServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();

        if (session.getAttribute("admin") != null) {
            //
            List<User> listOfUsers = userManager.getUsers();
            session.setAttribute("listOfUsers",listOfUsers);

            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
            dispatcher.forward(request, response);

        } else if (session.getAttribute("teacher") != null) {
            //
            List<User> listOfUsers = userManager.getUsers();
            session.setAttribute("listOfUsers",listOfUsers);

            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
            dispatcher.forward(request, response);

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
