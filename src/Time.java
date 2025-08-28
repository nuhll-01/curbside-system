import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public static void getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(dtf);
        System.out.println(formattedDate);
    }
}
