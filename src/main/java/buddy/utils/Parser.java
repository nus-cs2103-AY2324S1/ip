package buddy.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddy.TaskList;
import buddy.commands.AddDeadlineCommand;
import buddy.commands.AddEventCommand;
import buddy.commands.AddTodoCommand;
import buddy.commands.Command;
import buddy.commands.CommandType;
import buddy.commands.DeleteCommand;
import buddy.commands.ExitCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkAsDoneCommand;
import buddy.commands.MarkAsUndoneCommand;

/**
 * The Parser class deals with making sense of the user command.
 *
 * @author Lim Jin Yin
 */

public class Parser {

    // returns date in format MMM d yyyy
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return LocalDate.parse(dateString, inputFormatter);
    }

    // checks if date is in valid format
    private static void validateDate(String date) throws BuddyException {
        try {
            LocalDate d = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new BuddyException("OOPS! Please enter the date in YYYY-MM-DD format.");
        }
    }

    /**
     * Parses through user command and make sense of it.
     *
     * @param fullCommand The command given by the user.
     * @param tasks The current task list.
     * @return Command The respective command based on user input.
     * @throws BuddyException On invalid input.
     */
    public static Command parse(String fullCommand, TaskList tasks) throws BuddyException {
        String[] words = fullCommand.split(" ");

        CommandType commandType = CommandType.valueOf(words[0].toUpperCase());

        if (commandType.equals(CommandType.BYE)) {
            return new ExitCommand();
        } else if (commandType.equals(CommandType.LIST)) {
            return new ListCommand();
        } else if (commandType.equals(CommandType.MARK)) {
            int taskIndex = Integer.parseInt(words[1]);
            return new MarkAsDoneCommand(taskIndex);
        } else if (commandType.equals(CommandType.UNMARK)) {
            int taskIndex = Integer.parseInt(words[1]);
            return new MarkAsUndoneCommand(taskIndex);
        } else if (commandType.equals(CommandType.DELETE)) {
            int taskIndex = Integer.parseInt(words[1]);
            return new DeleteCommand(taskIndex);
        } else if (commandType.equals(CommandType.FIND)) {
            String keyword = fullCommand.replaceFirst("find ", "").trim();
            return new FindCommand(keyword);
        } else if (commandType.equals(CommandType.TODO)) {
            String[] parts = fullCommand.split(" ", 2);
            if (parts.length < 2 || parts[1].isEmpty()) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddTodoCommand(parts[1]);
            }
        } else if (commandType.equals(CommandType.DEADLINE)) {
            String[] parts = fullCommand.split(" ", 2);
            if (parts.length < 2 || parts[1].isEmpty()) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String[] deadlineParts = parts[1].split("/", 2);
                if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
                    throw new BuddyException("OOPS!!! Please include a description and deadline.");
                }
                String description = deadlineParts[0].trim();
                String deadlineBy = deadlineParts[1].replaceFirst("by ", "").trim();
                validateDate(deadlineBy);
                LocalDate d = LocalDate.parse(deadlineBy);
                return new AddDeadlineCommand(description, d);
            }
        } else if (commandType.equals(CommandType.EVENT)) {
            String[] parts = fullCommand.split(" ", 2);
            if (parts.length < 2 || parts[1].isEmpty()) {
                System.out.println("OOPS!!! The description of an event cannot be empty.");
            } else {
                String[] eventParts = parts[1].split("/", 3);
                if (eventParts.length < 3 || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
                    throw new BuddyException("OOPS!!! Please include event description, start and end date.");
                }
                String description = eventParts[0].trim();
                String eventStart = eventParts[1].replaceFirst("from ", "").trim();
                String eventEnd = eventParts[2].replaceFirst("to ", "").trim();
                validateDate(eventStart);
                validateDate(eventEnd);
                LocalDate startDate = LocalDate.parse(eventStart);
                LocalDate endDate = LocalDate.parse(eventEnd);
                return new AddEventCommand(description, startDate, endDate);
            }
        } else {
            throw new BuddyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;
    }
}
