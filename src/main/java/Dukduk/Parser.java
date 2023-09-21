package dukduk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class responsible for parsing user input and creating tasks.
 */
public class Parser {

    private String[] words;

    /**
     * A constructor for a parser
     *
     * @param input the user input
     */
    public Parser(String input) {
        assert input != null : "Input cannot be null";
        this.words = input.split(" ", 2);
    }

    /**
     * Return the command word of the user input
     *
     * @return the command word
     */
    public String getCommand() {
        assert this.words.length > 0 : "Words array is empty";
        return this.words[0];
    }

    /**
     * Parses user input and creates a task object based on the input.
     *
     * @param input The user input to be parsed.
     * @return A Task object created from the input.
     * @throws DukdukException If there is an error in the input or task creation.
     */
    public static Task parseTask(String input) throws DukdukException {
        assert input != null : "Input cannot be null";
        if (input.startsWith("todo")) {
            return parseToDoTask(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return parseEventTask(input);
        } else {
            throw new DukdukException("QUACKKK!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a ToDo task from the given input string.
     *
     * @param input The input string containing the task description.
     * @return A ToDo task.
     * @throws DukdukException If the description is empty.
     */
    private static Task parseToDoTask(String input) throws DukdukException {
        if (input.length() <= 5) {
            throw new DukdukException("QUACKKK!!! The description cannot be empty.");
        }
        return new ToDo(input.substring(5));
    }

    /**
     * Parses a Deadline task from the given input string.
     *
     * @param input The input string containing the task description and deadline.
     * @return A Deadline task.
     * @throws DukdukException If the deadline format is incorrect or the date/time format is incorrect.
     */
    private static Task parseDeadlineTask(String input) throws DukdukException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DukdukException("QUACKKK!!! The deadline format is incorrect. " +
                    "Use '/by' to specify the deadline.");
        }
        String description = input.substring(9, byIndex).trim();
        String byString = input.substring(byIndex + 3).trim();
        try {
            LocalDateTime by = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new DukdukException("QUACKKK!!! The date/time format is incorrect. " +
                    "Please use 'yyyy-MM-dd HHmm' format.");
        }
    }

    /**
     * Parses an Event task from the given input string.
     *
     * @param input The input string containing the task description and event timings.
     * @return An Event task.
     * @throws DukdukException If the event format is incorrect, timings are incorrect, or 'to' is not after 'from'.
     */
    private static Task parseEventTask(String input) throws DukdukException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DukdukException("QUACKKK!!! The event format is incorrect. Use '/from' " +
                    "and '/to' to specify the timings.");
        }
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            if (!toDateTime.isAfter(fromDateTime)) {
                throw new DukdukException("QUACKKK!!! The 'to' date/time must be " +
                        "after the 'from' date/time.");
            }
            return new Event(description, fromDateTime, toDateTime);
        } catch (DateTimeParseException e) {
            throw new DukdukException("QUACKKK!!! The date/time format is incorrect. " +
                    "Please use 'yyyy-MM-dd HHmm' format.");
        }
    }
}
