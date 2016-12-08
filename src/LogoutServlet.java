import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class LogoutServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        String style = (String) context.getAttribute("style");

        HttpSession session = request.getSession();
        session.invalidate();

        out.println("<html><body><link href=\"" + style + "\" rel=\"stylesheet\" type=\"text/css\"> ");
        request.getRequestDispatcher("link.html").include(request, response);
        out.println("You are successfully logged out!");
        out.println("</html></body>");
        out.close();
    }

}
