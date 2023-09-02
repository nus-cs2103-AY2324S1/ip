import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateUtils {
    /**
     * The date and time format used for all input in this program.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy kk");

    /**
     * THe date format used to display information in the program.
     */
    private static final SimpleDateFormat PRESENT_FORMAT = new SimpleDateFormat("MMM dd yyyy kk");

    /**
     * Parses dates and times entered.
     */
    public static Date parseDateTime(String dateTimeString) throws ParseException {
        return DATE_FORMAT.parse(dateTimeString);
    }

    public static String saveFormat(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String presentFormat(Date date) {
        return PRESENT_FORMAT.format(date);
    }

}
