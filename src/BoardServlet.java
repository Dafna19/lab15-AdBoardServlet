import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Для того, чтобы разместить, необходимо ввести логин и пароль (пройти аутентификацию).
 * При старте сервлет загружает базу пользователей и их паролей из текстового файла.
 * Просматривать объявления можно без аутентификации (ввода логина и пароля).
 * <p>
 * На главной странице находится ссылка “войти в систему” и показывается список объявлений.
 * После входа в систему добавляются ссылки: “выйти из системы”, “добавить объявление”.
 * <p>
 * 1) Для аутентификации необходимо использовать класс HttpSession
 * 2) Для перенаправления пользователя на другую страницу и
 * включения в страницу готовых кусков html кода
 * можно воспользоваться классом RequestDispatcher
 * 3) Протестировать, что при очистке cookie в браузере, пользователь выходит из системы.
 * <p>
 * Между перезагрузками сервера список объявлений можно не сохранять.
 * <p>
 * Добавить страницу администратора, который может удалять пользователей
 */
public class BoardServlet extends HttpServlet {
    private LinkedList<Ad> ads = new LinkedList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String uri = request.getRequestURI();
            System.out.println("uri: " + uri);///

            HttpSession session = request.getSession(false);//смотрим, есть ли сессия, false - новую не создаём

            Ad adv = (Ad)request.getAttribute("advert");
            if(adv != null){
                ads.add(adv);
            }

            if (session != null) {//уже вошли
                //больше нет ошибки
                request.getRequestDispatcher("links.html").include(request, response);
                out.println("Hello, " + session.getAttribute("name"));
            } else {//еще не вошли
                request.getRequestDispatcher("link.html").include(request, response);
            }

            //потом здесь выводить объявления
            for (Ad ad : ads) {
                out.println("<h3>" + ad.getHeading() + "</h3><p>" + ad.getText() + "</p>");
            }


        } catch (ServletException e) {
            System.out.println("ServletException");
            System.out.println(e.getMessage());
        } catch (NullPointerException n) {
            System.out.println("NullPointerException");
            n.printStackTrace();
        }

    }
}
