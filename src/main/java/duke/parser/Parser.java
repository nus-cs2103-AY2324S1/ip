package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

/**
 * Provides methods for parsing date and date-time strings
 * into Java LocalDateTime and LocalDate objects.
 */
public class Parser {
    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime, JTextArea chatArea) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (Exception e) {
            chatArea.append("Error parsing date-time: " + e.getMessage() + "\n");
            return null; // Return null to indicate an error
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string.
     * @return A LocalDate object.
     */
    public static LocalDate parseDate(String date, JTextArea chatArea) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            chatArea.append("Error parsing date: " + e.getMessage() + "\n");
            return null; // Return null to indicate an error
        }
    }
}
