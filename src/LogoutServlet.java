import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
/**
 *
 */
public class LogoutServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.invalidate();

        out.println("<html><body>");
        request.getRequestDispatcher("link.html").include(request, response);
        out.println("You are successfully logged out!");
        out.println("</html></body>");
        out.close();
    }

}
