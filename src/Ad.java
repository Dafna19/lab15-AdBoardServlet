import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Объявление содержит текст и заголовок (время размещения и имя пользователя).
 */
public class Ad {
    private String date;
    private String text;
    private String heading;

    public Ad(String name) {
        SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        date = form.format(new Date());
        heading = name + " at " + date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeading() {
        return heading;
    }

    public String getText() {
        return text;
    }
}
