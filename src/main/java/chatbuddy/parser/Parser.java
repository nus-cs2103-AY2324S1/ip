package chatbuddy.parser;

import chatbuddy.ChatBuddyException;
import chatbuddy.command.Command;
import chatbuddy.command.DeadlineCommand;
import chatbuddy.command.DeleteCommand;
import chatbuddy.command.EventCommand;
import chatbuddy.command.ExitCommand;
import chatbuddy.command.ListCommand;
import chatbuddy.command.MarkCommand;
import chatbuddy.command.TodoCommand;
import chatbuddy.command.UnmarkCommand;

import chatbuddy.task.Deadline;
import chatbuddy.task.Event;
import chatbuddy.task.Task;
import chatbuddy.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter FORMATTER_INPUT_DEADLINE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_INPUT_EVENT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static Command parse(String commandString) throws ChatBuddyException {
        String[] commandArray = commandString.split(" ", 2);
        String command = commandArray[0];
        String args = commandArray.length > 1 ? commandArray[1] : "";

        switch (command) {

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case TodoCommand.COMMAND_WORD:
            return prepareAddTodo(args);

        case DeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(args);

        case EventCommand.COMMAND_WORD:
            return prepareAddEvent(args);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(args);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(args);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(args);

        default:
            throw new ChatBuddyException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static TodoCommand prepareAddTodo(String args) throws ChatBuddyException {
        if (args.trim().equals("")) {
            throw new ChatBuddyException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(args);
    }

    private static DeadlineCommand prepareAddDeadline(String args) throws ChatBuddyException {
        String[] arr = args.split(" /by ");
        String taskDescription = arr[0].trim();
        // check validity of arguments
        if (taskDescription.equals("") || arr.length == 1 || arr[1].trim().equals("")) {
            throw new ChatBuddyException("Please input deadlines in the format " +
                    "'deadline [task description] /by [deadline in dd/MM/yyyy]'.\n" +
                    "The task description and deadline cannot be empty.");
        }

        // parse deadline string to LocalDate object
        LocalDate date = parseDate(arr[1].trim());
        return new DeadlineCommand(taskDescription, date);
    }

    private static EventCommand prepareAddEvent(String args) throws ChatBuddyException {
        // check validity of arguments
        String[] arr = args.split(" /from ");
        String taskDescription = arr[0].trim();
        if (taskDescription.equals("") || arr.length == 1 || arr[1].trim().equals("")) {
            throw new ChatBuddyException("Please input events in the format " +
                    "'event [task description] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]'.\n" +
                    "The task description, from datetime and to datetime cannot be empty.");
        }

        String[] dateTimeArgs = arr[1].trim().split(" /to ");
        if (dateTimeArgs[0].trim().equals("") || dateTimeArgs.length == 1 || dateTimeArgs[1].trim().equals("")) {
            throw new ChatBuddyException("Please input events in the format " +
                    "'event [task description] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]'.\n" +
                    "The task description, from datetime and to datetime cannot be empty.");
        }

        // parse date time arguments to LocalDateTime object
        LocalDateTime from = parseDateTime(dateTimeArgs[0].trim());
        LocalDateTime to = parseDateTime(dateTimeArgs[1].trim());
        return new EventCommand(taskDescription, from, to);
    }

    private static DeleteCommand prepareDelete(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    private static MarkCommand prepareMark(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    private static UnmarkCommand prepareUnmark(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    private static LocalDate parseDate(String dateString) throws ChatBuddyException {
        try {
            LocalDate date = LocalDate.parse(dateString, FORMATTER_INPUT_DEADLINE);
            return date;
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Please input the deadline date in the format dd/MM/yyyy.");
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeString) throws ChatBuddyException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, FORMATTER_INPUT_EVENT);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Please input the datetime in the format dd/MM/yyyy HHmm.");
        }
    }

    public static Task parseToTask(String taskString) throws ChatBuddyException {
        String[] taskData = taskString.split(" \\| ");
        String taskType = taskData[0];
        boolean isCompleted = taskData[1].equals("1");
        String taskDescription = taskData[2];

        // create task object
        Task task;
        if (taskType.equals("T")) {
            task = new ToDo(taskDescription);
        } else if (taskType.equals("D")) {
            LocalDate by = parseDate(taskData[3]);
            task = new Deadline(taskDescription, by);
        } else if (taskType.equals("E")) {
            LocalDateTime from = parseDateTime(taskData[3]);
            LocalDateTime to = parseDateTime(taskData[4]);
            task = new Event(taskDescription, from, to);
        } else {
            throw new ChatBuddyException("Error parsing data from file");
        }

        // update completion status
        if (isCompleted) {
            task.markAsDone();
        }

        return task;
    }
}
