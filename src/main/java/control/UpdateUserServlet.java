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

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 65841320986532089L;

    public UpdateUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminUpdateUser.jsp");
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
        UserManager userManager = new UserManager();

        //
        String email = request.getParameter("email");
        String newEmail = request.getParameter("new-email");
        String newName = request.getParameter("new-name");
        String newPassword = request.getParameter("new-password");
        String newPosition = request.getParameter("new-rank");
        User user = new User(newEmail, newName, newPassword, newPosition);

        //
        if (userManager.updateUser(email, user)) {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request, response);
        } else {
            //
            this.doGet(request, response);

        }

    }
}
