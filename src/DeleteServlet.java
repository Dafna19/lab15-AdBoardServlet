import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Наташа on 08.12.2016.
 */
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        ConcurrentHashMap<String, String> userList = (ConcurrentHashMap) context.getAttribute("list");

        userList.remove(request.getParameter("user"));
        System.out.println("removed " + request.getParameter("user"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin");
        dispatcher.forward(request, response);
    }
}
