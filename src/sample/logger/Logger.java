package sample.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void log(String message){
        System.out.println(LocalDateTime.now().format(formatter) + ": " + message);
    }
}
