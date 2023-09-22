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
import buddy.commands.FindCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkAsDoneCommand;
import buddy.commands.MarkAsUndoneCommand;
import buddy.commands.UpdateDateCommand;
import buddy.commands.UpdateDescriptionCommand;

/**
 * The Parser class deals with making sense of the user command.
 *
 * @author Lim Jin Yin
 */

public class Parser {

    // returns LocalDate in format MMM d yyyy
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return LocalDate.parse(dateString, inputFormatter);
    }

    // checks if date is in valid format
    private static void validateDate(String date) throws BuddyException {
        assert !date.isBlank() : "date should not be blank";
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
        assert tasks.getSize() >= 0 : "size of task list should be more than or equal to 0";
        String[] words = fullCommand.split(" ");
        CommandType commandType = CommandType.valueOf(words[0].toUpperCase());
      
        if (commandType.equals(CommandType.BYE)) {
            return new ExitCommand();
        } else if (commandType.equals(CommandType.LIST)) {
            return new ListCommand();
        } else if (commandType.equals(CommandType.MARK)) {
            return parseMark(fullCommand);
        } else if (commandType.equals(CommandType.UNMARK)) {
            return parseUnmark(fullCommand);
        } else if (commandType.equals(CommandType.DELETE)) {
            return parseDelete(fullCommand);
        } else if (commandType.equals(CommandType.FIND)) {
            return parseFind(fullCommand);
        } else if (commandType.equals(CommandType.UPDATE)) {
            return parseUpdate(fullCommand);
        } else if (commandType.equals(CommandType.TODO)) {
            return parseTodo(fullCommand);
        } else if (commandType.equals(CommandType.DEADLINE)) {
            return parseDeadline(fullCommand);
        } else if (commandType.equals(CommandType.EVENT)) {
            return parseEvent(fullCommand);
        } else {
            throw new BuddyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseMark(String command) throws BuddyException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyException("OOPS!!! Please enter in the format: mark <TASK_INDEX>");
        }
        int taskIndex = Integer.parseInt(commandParts[1]);
        return new MarkAsDoneCommand(taskIndex);
    }

    private static Command parseUnmark(String command) throws BuddyException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyException("OOPS!!! Please enter in the format: unmark <TASK_INDEX>");
        }
        int taskIndex = Integer.parseInt(commandParts[1]);
        return new MarkAsUndoneCommand(taskIndex);
    }

    private static Command parseDelete(String command) throws BuddyException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyException("OOPS!!! Please enter in the format: delete <TASK_INDEX>");
        }
        int taskIndex = Integer.parseInt(commandParts[1]);
        return new DeleteCommand(taskIndex);
    }

    private static Command parseFind(String command) throws BuddyException {
        String[] commandParts = command.split(" ", 2);
        if (commandParts.length < 2) {
            throw new BuddyException("OOPS!!! Please enter in the format: find <KEYWORD>");
        }
        String keyword = commandParts[1].trim();
        return new FindCommand(keyword);
    }

    private static Command parseUpdate(String command) throws BuddyException {
        String correctFormat = "update [task number] /[desc/by/from/to] [newDesc/newDate]";
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! Please enter in the format:\n" + correctFormat);
        }
        String[] updateParts = parts[1].split("/", 2);
        if (updateParts.length < 2 || updateParts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! Please enter in the format:\n" + correctFormat);
        }
        int taskIndex = Integer.parseInt(updateParts[0].trim());
        String[] words = updateParts[1].split(" ", 2);
        String fieldToUpdate = words[0].trim();
        String update = words[1].trim();

        if (fieldToUpdate.equalsIgnoreCase("desc")) {
            return new UpdateDescriptionCommand(taskIndex, update);
        } else if (fieldToUpdate.equalsIgnoreCase("by")
                || fieldToUpdate.equalsIgnoreCase("from")
                || fieldToUpdate.equalsIgnoreCase("to")) {
            validateDate(update);
            LocalDate newDate = LocalDate.parse(update);
            return new UpdateDateCommand(taskIndex, fieldToUpdate, newDate);
        } else {
            throw new BuddyException("OOPS!!! Please enter in the format:\n" + correctFormat);
        }
    }

    private static Command parseTodo(String command) throws BuddyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = parts[1].trim();
        return new AddTodoCommand(description);
    }

    private static Command parseDeadline(String command) throws BuddyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = parts[1].split("/", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! Please include a description and deadline.");
        }
        String description = deadlineParts[0].trim();
        String deadlineBy = deadlineParts[1].replaceFirst("by ", "").trim();
        validateDate(deadlineBy);
        LocalDate deadline = LocalDate.parse(deadlineBy);
        return new AddDeadlineCommand(description, deadline);
    }

    private static Command parseEvent(String command) throws BuddyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyException("OOPS!!! The description of an event cannot be empty.");
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
    }
}
