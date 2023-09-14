package joi.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParse {
    public static Date parseDateTime(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = new Date();
        try {
           date = dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("Parsing date time error: " + e.getMessage());
        }
        return date;
    }

}
