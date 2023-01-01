package control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;
import java.util.Enumeration;

@WebServlet(name = "LogOutServlet", value = "/Logout")
public class LogOutServlet extends HttpServlet {
    //
    @Serial
    private static final long serialVersionUID = 101645573239L;

    public LogOutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);

        // Remove session Attributes and invalidate it
        // now basicly the session becomes clean containing no information
        // resulting in a log out
        if (session != null) {
            //
            Enumeration<String> attributeNames = session.getAttributeNames();

            while (attributeNames.hasMoreElements()) {
                String attribute = attributeNames.nextElement();

                session.removeAttribute(attribute);

            }

            //
            session.invalidate();
        }


        //
        session = request.getSession();
        session.setAttribute("session", null);

        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.include(request, response);


    }
}