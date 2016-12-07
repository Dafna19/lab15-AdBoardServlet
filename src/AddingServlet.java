import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * создаёт объявление и передаёт управление BoardServlet
 */
public class AddingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddingServlet uri: " + request.getRequestURI());////

        HttpSession session = request.getSession(false);

        String user = (String) session.getAttribute("name");
        Ad adv = new Ad(user);
        String text = request.getParameter("text");
        adv.setText(text);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Board");
        request.setAttribute("advert", adv);
        dispatcher.forward(request, response);
    }
}
