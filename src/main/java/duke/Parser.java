package duke;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the string
 */
public class Parser {

    /**
     * parses a string to match the format to create a deadline task
     * @param input String to be parsed
     * @return A string suitable to create a new deadline task
     */
    public static String convertDeadlineFormat(String input) {
        assert input.contains("/by");
        Pattern pattern = Pattern.compile("by: (\\w{3} \\d{2} \\d{4}, \\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String dateAndTime = matcher.group(1);
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime,
                    DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            String result = input.replaceFirst("by: " + dateAndTime, "/by " + formattedDateTime);
            result = result.replace(")", "");
            result = result.replace("(", "");
            return result;
        } else {
            return input;
        }
    }
    /**
     * parses a string to match the format to create an event task
     * @param input String to be parsed
     * @return A string suitable to create a new event task
     */
    public static String convertEventFormat(String input) {
        // Define a regular expression pattern to match the date and time strings
        assert input.contains("/to");
        assert input.contains("/from");
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
                String replacement = "/from " + outputDateFormat.format(fromDate) + " /to "
                        + outputDateFormat.format(toDate);
                matcher.appendReplacement(sb, replacement);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * Retrieves the localDateTime from the input
     * @param input input to retrieve lcoalDateTime from
     * @return localDateTime
     */
    public static LocalDateTime retrieveDeadlineTime(String input) {
        String[] parts = input.split("\\s+");
        String dateStr = parts[1]; // Date part
        String timeStr = parts[2]; // Time part
        String dateTimeStr = dateStr + " " + timeStr;
        System.out.println("DateStr is: " + dateTimeStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * Extracts out the time for an Event.
     * @param input String representation of the Event
     * @return array of times (from time and to time)
     * @throws InvalidInputException
     */
    public static LocalDateTime[] retrieveEventTime(String input) throws InvalidInputException {
        String[] parts = input.split("/from | /to ");
        if (parts.length != 3) {
            throw new InvalidInputException();
        }
        String fromDateTimeStr = parts[1];
        String toDateTimeStr = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeStr, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeStr, formatter);
        LocalDateTime[] dateTimeArray = { fromDateTime, toDateTime };
        return dateTimeArray;

    }
}
