package control;

import model.UserManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 19840339L;

    public DeleteUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminDeleteUser.jsp");
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

        //
        if(userManager.deleteUser(email)){
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);
        }else{
            //
            this.doGet(request,response);

        }

    }
}
