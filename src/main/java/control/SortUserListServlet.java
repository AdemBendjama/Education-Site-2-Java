package control;

import model.User;
import model.UserManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortUserListServlet", value = "/sort")
public class SortUserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doGet(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        UserManager userManager = new UserManager();
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        String sortBy = request.getParameter("sortBy");

        switch (sortBy) {
            case "email" -> {
                //
                List<User> listOfUsers = userManager.sortByEmail();
                session.setAttribute("listOfUsers", listOfUsers);
                session.setAttribute("optionSelected","email");

                //
                dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
                dispatcher.forward(request, response);

            }
            case "name" -> {
                //
                List<User> listOfUsers = userManager.sortByName();
                session.setAttribute("listOfUsers", listOfUsers);
                session.setAttribute("optionSelected","name");

                //
                dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
                dispatcher.forward(request, response);

            }
            case "rank" -> {
                //
                List<User> listOfUsers = userManager.sortByRank();
                session.setAttribute("listOfUsers", listOfUsers);
                session.setAttribute("optionSelected","rank");

                //
                dispatcher = request.getRequestDispatcher("/WEB-INF/Admin/AdminMain.jsp");
                dispatcher.forward(request, response);

            }
            default ->
                //
                    this.doGet(request, response);

        }
    }
}
