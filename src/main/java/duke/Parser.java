package duke;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static String convertDeadlineFormat(String input) {
        Pattern pattern = Pattern.compile("by: (\\w{3} \\d{2} \\d{4}, \\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String dateAndTime = matcher.group(1);
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            String result = input.replaceFirst("by: " + dateAndTime, "/by " + formattedDateTime);
            result = result.replace(")", "");
            result = result.replace("(", "");
            return result;
        } else {
            return input;
        }
    }
    public static String convertEventFormat(String input) {
        // Define a regular expression pattern to match the date and time strings
        Pattern pattern = Pattern.compile("\\((from: ([^)]+) to: ([^)]+))\\)");
        Matcher matcher = pattern.matcher(input);

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM dd yyyy, HH:mm");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        // Find and replace matches in the input string
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                Date fromDate = inputDateFormat.parse(matcher.group(2));
                Date toDate = inputDateFormat.parse(matcher.group(3));

                String replacement = "/from " + outputDateFormat.format(fromDate) + " /to " + outputDateFormat.format(toDate);
                matcher.appendReplacement(sb, replacement);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
