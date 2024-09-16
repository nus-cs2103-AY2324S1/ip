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
import buddy.commands.HelpCommand;
import buddy.commands.ListCommand;
import buddy.commands.MarkAsDoneCommand;
import buddy.commands.MarkAsUndoneCommand;
import buddy.commands.UpdateDateCommand;
import buddy.commands.UpdateDescriptionCommand;
import buddy.exceptions.BuddyCommandException;

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
    private static void validateDate(String date) throws BuddyCommandException {
        assert !date.isBlank() : "date should not be blank";
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new BuddyCommandException("Enter the date in YYYY-MM-DD format");
        }
    }

    private static void validateIndex(String string) throws BuddyCommandException {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new BuddyCommandException("Enter the index as a positive integer");
        }
    }

    /**
     * Parses through user command and make sense of it.
     *
     * @param fullCommand The command given by the user.
     * @param tasks The current task list.
     * @return Command The respective command based on user input.
     * @throws BuddyCommandException On invalid input.
     */
    public static Command parse(String fullCommand, TaskList tasks) throws BuddyCommandException {
        assert tasks.getSize() >= 0 : "size of task list should be more than or equal to 0";
        String[] words = fullCommand.split(" ");
        try {
            CommandType commandType = CommandType.valueOf(words[0].toUpperCase());
            if (commandType.equals(CommandType.BYE)) {
                return new ExitCommand();
            } else if (commandType.equals(CommandType.HELP)) {
                return new HelpCommand();
            } else if (commandType.equals(CommandType.LIST)) {
                return new ListCommand();
            } else if (commandType.equals(CommandType.MARK)) {
                return parseMark(fullCommand, tasks);
            } else if (commandType.equals(CommandType.UNMARK)) {
                return parseUnmark(fullCommand, tasks);
            } else if (commandType.equals(CommandType.DELETE)) {
                return parseDelete(fullCommand, tasks);
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
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new BuddyCommandException("To see the list of commands, send 'help'!");
        }
    }

    private static Command parseMark(String command, TaskList taskList) throws BuddyCommandException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyCommandException(MarkAsDoneCommand.MESSAGE_FORMAT);
        }
        validateIndex(commandParts[1]);
        int taskIndex = Integer.parseInt(commandParts[1]);
        int numberOfTasks = taskList.getSize();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new BuddyCommandException(MarkAsDoneCommand.MESSAGE_INDEX_BOUND + numberOfTasks);
        }
        return new MarkAsDoneCommand(taskIndex);
    }

    private static Command parseUnmark(String command, TaskList taskList) throws BuddyCommandException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyCommandException(MarkAsUndoneCommand.MESSAGE_FORMAT);
        }
        validateIndex(commandParts[1]);
        int taskIndex = Integer.parseInt(commandParts[1]);
        int numberOfTasks = taskList.getSize();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new BuddyCommandException(MarkAsUndoneCommand.MESSAGE_INDEX_BOUND + numberOfTasks);
        }
        return new MarkAsUndoneCommand(taskIndex);
    }

    private static Command parseDelete(String command, TaskList taskList) throws BuddyCommandException {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new BuddyCommandException(DeleteCommand.MESSAGE_FORMAT);
        }
        validateIndex(commandParts[1]);
        int taskIndex = Integer.parseInt(commandParts[1]);
        int numberOfTasks = taskList.getSize();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new BuddyCommandException(DeleteCommand.MESSAGE_INDEX_BOUND + numberOfTasks);
        }
        return new DeleteCommand(taskIndex);
    }

    private static Command parseFind(String command) throws BuddyCommandException {
        String[] commandParts = command.split(" ", 2);
        if (commandParts.length < 2) {
            throw new BuddyCommandException(FindCommand.MESSAGE_FORMAT);
        }
        String keyword = commandParts[1].trim();
        return new FindCommand(keyword);
    }

    private static Command parseUpdate(String command) throws BuddyCommandException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyCommandException("To update:\n" + UpdateDescriptionCommand.MESSAGE_FORMAT
                    + UpdateDateCommand.MESSAGE_FORMAT);
        }
        String[] updateParts = parts[1].split("/", 2);
        if (updateParts.length < 2 || updateParts[1].isEmpty()) {
            throw new BuddyCommandException("To update:\n" + UpdateDescriptionCommand.MESSAGE_FORMAT
                    + " \n" + UpdateDateCommand.MESSAGE_FORMAT);
        }
        validateIndex(updateParts[0].trim());
        int taskIndex = Integer.parseInt(updateParts[0].trim());
        String[] words = updateParts[1].split(" ", 2);
        if (words.length < 2 || updateParts[1].isEmpty()) {
            throw new BuddyCommandException("To update:\n" + UpdateDescriptionCommand.MESSAGE_FORMAT
                    + UpdateDateCommand.MESSAGE_FORMAT);
        }
        String fieldToUpdate = words[0].trim();
        String update = words[1].trim();
        if (fieldToUpdate.equalsIgnoreCase("desc")) {
            return new UpdateDescriptionCommand(taskIndex, update);
        } else if (fieldToUpdate.equalsIgnoreCase("by") || fieldToUpdate.equalsIgnoreCase("from")
                || fieldToUpdate.equalsIgnoreCase("to")) {
            validateDate(update);
            LocalDate newDate = LocalDate.parse(update);
            return new UpdateDateCommand(taskIndex, fieldToUpdate, newDate);
        } else {
            throw new BuddyCommandException("To update:\n" + UpdateDescriptionCommand.MESSAGE_FORMAT
                    + UpdateDateCommand.MESSAGE_FORMAT);
        }
    }

    private static Command parseTodo(String command) throws BuddyCommandException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyCommandException(AddTodoCommand.MESSAGE_FORMAT);
        }
        String description = parts[1].trim();
        return new AddTodoCommand(description);
    }

    private static Command parseDeadline(String command) throws BuddyCommandException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyCommandException(AddDeadlineCommand.MESSAGE_FORMAT);
        }
        String[] deadlineParts = parts[1].split("/", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            throw new BuddyCommandException(AddDeadlineCommand.MESSAGE_FORMAT);
        }
        String description = deadlineParts[0].trim();
        String deadlineBy = deadlineParts[1].replaceFirst("by ", "").trim();
        validateDate(deadlineBy);
        LocalDate deadline = LocalDate.parse(deadlineBy);
        return new AddDeadlineCommand(description, deadline);
    }

    private static Command parseEvent(String command) throws BuddyCommandException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new BuddyCommandException(AddEventCommand.MESSAGE_FORMAT);
        } else {
            String[] eventParts = parts[1].split("/", 3);
            if (eventParts.length < 3 || eventParts[0].isEmpty()
                    || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
                throw new BuddyCommandException(AddEventCommand.MESSAGE_FORMAT);
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
