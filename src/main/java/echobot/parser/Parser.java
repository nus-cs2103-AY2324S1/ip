package echobot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import echobot.task.Task;
import echobot.ui.Ui;


/**
 * Provides methods for parsing date and date-time strings
 * into Java LocalDateTime and LocalDate objects.
 */
public class Parser {
    private Ui ui;
    private ArrayList<Task> tasks;

    /**
     * Creates a new Parser instance.
     *
     * @param ui    The user interface component.
     * @param tasks The list of tasks to be parsed and processed.
     */
    public Parser(Ui ui, ArrayList<Task> tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string.
     * @return A LocalDate object.
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
