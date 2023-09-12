package duke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String convertDate(String inputDateStr) {
        try {
            // Define a SimpleDateFormat for parsing the input date
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM dd yyyy");

            // Parse the input date string to obtain a Date object
            Date inputDate = inputDateFormat.parse(inputDateStr);

            // Convert the Date to a LocalDate if needed
            // For this example, we'll convert it to a string in "yyyy/MM/dd" format
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");

            // Format the Date as a string in "yyyy/MM/dd" format
            return outputDateFormat.format(inputDate);
        } catch (ParseException e) {
            // Handle any parsing errors
            return "Invalid date format: " + e.getMessage();
        }
    }

}
