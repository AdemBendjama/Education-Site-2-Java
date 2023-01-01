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
import java.io.Serial;
import java.util.List;

@WebServlet(name = "UserListServlet", value = "/listUsers")
public class UserListServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 102839875784239L;

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
            session.setAttribute("listOfUsers", listOfUsers);

            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
            dispatcher.forward(request, response);

        } else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);

        }
    }

}
