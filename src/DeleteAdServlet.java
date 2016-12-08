import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Наташа on 08.12.2016.
 */
public class DeleteAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        LinkedList<Ad> list = (LinkedList<Ad>)context.getAttribute("adList");

        System.out.println("in DeleteAdServlet");///
        String head = request.getParameter("heading");
        for (Ad ad : list){
            if(ad.getHeading().equals(head)){
                list.remove(ad);
                break;
            }
        }
        request.getRequestDispatcher("Board").forward(request, response);
    }
}
