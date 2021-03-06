import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class LoginServlet extends HttpServlet {
    private ConcurrentHashMap<String, String> users;

    @Override
    public void init() throws ServletException {
        try {
            users = new ConcurrentHashMap<>();
            InputStream inputFile = new FileInputStream("C:\\Users\\Наташа\\Dropbox\\Учёба\\прога\\ТехПрог\\15\\src\\users.txt");
            Scanner file = new Scanner(inputFile);
            while (file.hasNext()) {
                users.put(file.next(), file.next());//имя, пароль
            }
            ///////
            System.out.println(users.toString());
            ///////
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("error while closing the file");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        String style = (String) context.getAttribute("style");

        context.setAttribute("list", users);

        out.println("<html><body><link href=\"" + style + "\" rel=\"stylesheet\" type=\"text/css\"> ");
        if (users.containsKey(name) && password.equals(users.get(name))) {//вошли
            if (name.equals("admin"))
                request.getRequestDispatcher("adminLinks.html").include(request, response);
            else
                request.getRequestDispatcher("links.html").include(request, response);
            out.println("Welcome, " + name + "!");
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
        } else {
            request.getRequestDispatcher("links.html").include(request, response);
            out.println("Incorrect username or password. Please, try again.");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.println("</html></body>");
        out.close();
    }
}
