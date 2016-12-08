import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Наташа on 08.12.2016.
 */
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        String style = (String) context.getAttribute("style");
        ConcurrentHashMap<String, String> userList = (ConcurrentHashMap) context.getAttribute("list");
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);//////


        out.println("<html><body><link href=\"" + style + "\" rel=\"stylesheet\" type=\"text/css\"> ");
        request.getRequestDispatcher("adminLinks.html").include(request, response);

        out.println("<form action=\"Delete\" method=\"get\">");
        out.println("<br>Choose:");
        out.println("<select name=\"user\">");
        for (String name : userList.keySet()) {
            out.println("<option value=\"" + name + "\">" + name + "</option>");
        }
        out.println("<input type=\"submit\" value=\"Delete\"></select></form>");


        out.println("</html></body>");
        out.close();
    }
}
