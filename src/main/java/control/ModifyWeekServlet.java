package control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModifyWeekServlet", value = "/modifyWeek")
public class ModifyWeekServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        //
        if(session.getAttribute("teacher") != null){
            //
            dispatcher = request.getRequestDispatcher("/WEB-INF/Teacher/TeacherModifyWeek.jsp");
            dispatcher.include(request,response);
        }else {
            //
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
