package control;

import model.AdminManager;
import model.LoginAuthenticator;
import model.User;
import model.UserManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "AddUserServlet", value = "/addUser")
public class AddUserServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 1076546813239L;

    public AddUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminAddUser.jsp");
            dispatcher.include(request, response);


        } else {
            //
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        UserManager userManager = new UserManager();

        //
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String rank = request.getParameter("rank");
        User user = new User(email,name,password,rank);

        //
        if(userManager.addUser(user)){
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);
        }else{
            //
            this.doGet(request,response);

        }

    }
}
