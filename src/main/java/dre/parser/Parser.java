package dre.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import dre.task.Task;
import dre.task.ToDo;
import dre.task.Event;
import dre.task.Deadline;
import dre.command.AddCommand;
import dre.command.DeleteCommand;
import dre.command.ExitCommand;
import dre.command.MarkCommand;
import dre.command.UnmarkCommand;
import dre.command.Command;
import dre.command.ListCommand;
import dre.exception.DreException;

/**
 * Represents a parser that makes sense of user input commands.
 */
public class Parser {

    /**
     * Parses a given line and returns a Task object.
     *
     * @param line The string representation of the task.
     * @return The corresponding Task object, or null if invalid line.
     */
    public static Task parseTask(String line) {
        String type = line.substring(0, 1);
        boolean isDone = line.charAt(2) == 'X';
        String description;
        Task task = null;

        switch (type) {
            case "T":
                String[] todoInfo = line.split("\\|");
                task = new ToDo(todoInfo[2]);
                break;
            case "D":
                String[] deadlineInfo = line.split("\\|");
                description = deadlineInfo[2].trim();
                LocalDate byDate = parseDate(deadlineInfo[3].trim());
                task = new Deadline(description, byDate);
                break;

            case "E":
                String[] eventInfo = line.split("\\|");
                description = eventInfo[2];
                LocalDate fromDate = parseDate(eventInfo[3]);
                LocalDate toDate = parseDate(eventInfo[4]);
                task = new Event(description, fromDate, toDate);
        }

        if (isDone) {
            task.done();
        }
        return task; //instead of adding to list, just return the dre.task.
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateString The date in "yyyy-MM-dd" format.
     * @return The LocalDate representation of the dateString.
     */
    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, inputFormatter);
    }

    /**
     * Parses the user input to determine which command to execute.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object.
     * @throws DreException If the input command is unknown or invalid.
     */
    public static Command parse(String input) throws DreException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (words.length < 2) {
                    throw new DreException("Please specify the task index to mark.");
                }
                return new MarkCommand(Integer.parseInt(words[1].trim()));
            case "unmark":
                if (words.length < 2) {
                    throw new DreException("Please specify the task index to unmark.");
                }
                return new UnmarkCommand(Integer.parseInt(words[1].trim()));
            case "delete":
                if (words.length < 2) {
                    throw new DreException("Please specify the task index to delete.");
                }
                return new DeleteCommand(Integer.parseInt(words[1].trim()));
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new DreException("The description of a todo cannot be empty.");
                }
                return new AddCommand(new ToDo(words[1].trim()));
            case "deadline":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new DreException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = words[1].split("/by", 2);
                if (deadlineParts.length < 2) {
                    throw new DreException("Please specify the date using /by.");
                }
                LocalDate byDate;
                try {
                    byDate = LocalDate.parse(deadlineParts[1].trim());
                } catch (DateTimeParseException e) {
                    throw new DreException("Please use the correct date format: YYYY-MM-DD.");
                }
                return new AddCommand(new Deadline(deadlineParts[0].trim(), byDate));
            case "event":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new DreException("The description of an event cannot be empty.");
                }
                String[] eventParts = words[1].split("/from | /to ", 3);
                if (eventParts.length < 3) {
                    throw new DreException("Please specify the date range using /from and /to.");
                }
                LocalDate fromDate, toDate;
                try {
                    fromDate = LocalDate.parse(eventParts[1].trim());
                    toDate = LocalDate.parse(eventParts[2].trim());
                } catch (DateTimeParseException e) {
                    throw new DreException("Please use the correct date format: YYYY-MM-DD.");
                }
                return new AddCommand(new Event(eventParts[0].trim(), fromDate, toDate));
            case "find":
                if (words.length < 2 || words[1].isEmpty()) {
                    throw new DreException("The search keyword cannot be empty.");
                }
                return new FindCommand(words[1]);
            default:
                throw new DreException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
