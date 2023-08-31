package helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DateTimeDetection {

    public static LocalDateTime detectDateTime(String input) {

        if (isDateTime(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } else if (isDate(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(input, formatter);
            return date.atTime(LocalTime.of(23, 59));
        } else if (isTime(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(input, formatter);
            return LocalDateTime.of(LocalDate.now(), time);
        } else {
            System.out.println("Invalid format.");
            return null;
        }
    }

    public static boolean isDateTime(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDate.parse(input, dateFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDate(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, dateFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTime(String input) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(input, timeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(outputFormatter);
    }
}
