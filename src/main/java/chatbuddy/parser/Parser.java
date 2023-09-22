package chatbuddy.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbuddy.ChatBuddyException;
import chatbuddy.command.Command;
import chatbuddy.command.DeadlineCommand;
import chatbuddy.command.DeleteCommand;
import chatbuddy.command.EventCommand;
import chatbuddy.command.ExitCommand;
import chatbuddy.command.FindCommand;
import chatbuddy.command.ListCommand;
import chatbuddy.command.MarkCommand;
import chatbuddy.command.TagCommand;
import chatbuddy.command.TodoCommand;
import chatbuddy.command.UnmarkCommand;
import chatbuddy.task.Deadline;
import chatbuddy.task.Event;
import chatbuddy.task.Task;
import chatbuddy.task.ToDo;

/**
 * Parser represents a class to handle the parsing of user commands.
 * Parser can also parse task data from storage.
 */
public class Parser {
    /** The formatter for deadline inputs. */
    private static final DateTimeFormatter FORMATTER_INPUT_DEADLINE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /** The formatter for event datetime inputs. */
    private static final DateTimeFormatter FORMATTER_INPUT_EVENT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses the user command and returns a Command.
     *
     * @param commandString The command inputted by the user.
     * @return The command to execute.
     * @throws ChatBuddyException If the command is invalid.
     */
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

        case FindCommand.COMMAND_WORD:
            return new FindCommand(args);

        case TagCommand.COMMAND_WORD:
            return prepareTag(args);

        default:
            throw new ChatBuddyException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns a TodoCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the description of the task.
     * @return The TodoCommand to execute.
     * @throws ChatBuddyException If the description of the task is empty or consists only of whitespace.
     */
    private static TodoCommand prepareAddTodo(String args) throws ChatBuddyException {
        boolean isDescriptionEmpty = args.trim().equals("");
        if (isDescriptionEmpty) {
            throw new ChatBuddyException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(args);
    }

    /**
     * Returns a DeadlineCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the description and deadline of the task.
     * @return The DeadlineCommand to execute.
     * @throws ChatBuddyException If the description or deadline of the task is empty or consists only of whitespace.
     */
    private static DeadlineCommand prepareAddDeadline(String args) throws ChatBuddyException {
        String[] arr = args.split(" /by ");
        String taskDescription = arr[0].trim();

        // check validity of arguments
        boolean isDescriptionEmpty = taskDescription.equals("");
        boolean isDeadlineGiven = arr.length > 1 && !arr[1].trim().equals("");
        if (isDescriptionEmpty || !isDeadlineGiven) {
            throw new ChatBuddyException("Please input deadlines in the format "
                    + "'deadline [task description] /by [deadline in dd/MM/yyyy]'.\n"
                    + "The task description and deadline cannot be empty.");
        }

        // parse deadline string to LocalDate object
        LocalDate date = parseDate(arr[1].trim());
        return new DeadlineCommand(taskDescription, date);
    }

    /**
     * Returns an EventCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the description,
     *             the 'from' datetime and the 'to' datetime task.
     * @return The EventCommand to execute.
     * @throws ChatBuddyException If the description, 'from' or 'to' datetime is empty of consists only of whitespaces.
     */
    private static EventCommand prepareAddEvent(String args) throws ChatBuddyException {
        // check validity of arguments
        String[] arr = args.split(" /from ");
        String taskDescription = arr[0].trim();

        boolean isDescriptionEmpty = taskDescription.equals("");
        boolean isFromKeywordGiven = arr.length > 1;
        if (isDescriptionEmpty || !isFromKeywordGiven) {
            throw new ChatBuddyException("Please input events in the format "
                    + "'event [task description] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]'.\n"
                    + "The task description, from datetime and to datetime cannot be empty.");
        }

        String[] dateTimeArgs = arr[1].trim().split(" /to ");
        boolean isFromGiven = !dateTimeArgs[0].trim().equals("");
        boolean isToGiven = dateTimeArgs.length > 1 && !dateTimeArgs[1].trim().equals("");
        if (!isFromGiven || !isToGiven) {
            throw new ChatBuddyException("Please input events in the format "
                    + "'event [task description] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]'.\n"
                    + "The task description, from datetime and to datetime cannot be empty.");
        }

        // parse date time arguments to LocalDateTime object
        LocalDateTime from = parseDateTime(dateTimeArgs[0].trim());
        LocalDateTime to = parseDateTime(dateTimeArgs[1].trim());
        return new EventCommand(taskDescription, from, to);
    }

    /**
     * Returns a DeleteCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the task number of the task to delete.
     * @return The DeleteCommand to execute.
     * @throws ChatBuddyException If the task number is not a numerical number.
     */
    private static DeleteCommand prepareDelete(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    /**
     * Returns a MarkCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the task number of the task to mark as done.
     * @return The MarkCommand to execute.
     * @throws ChatBuddyException If the task number is not a numerical number.
     */
    private static MarkCommand prepareMark(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    /**
     * Returns an UnmarkCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the task number of the task to mark as not done.
     * @return The UnmarkCommand to execute.
     * @throws ChatBuddyException If the task number is not a numerical number.
     */
    private static UnmarkCommand prepareUnmark(String args) throws ChatBuddyException {
        try {
            int taskNum = Integer.parseInt(args.trim());
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    /**
     * Returns a TagCommand based on the arguments provided by the user.
     *
     * @param args The arguments provided by the user. This should be the task number of the task and the new tag.
     * @return The TagCommand to execute.
     * @throws ChatBuddyException If the task number is not a numerical number.
     */
    private static TagCommand prepareTag(String args) throws ChatBuddyException {
        String[] argArray = args.split(" ", 2);
        try {
            int taskNum = Integer.parseInt(argArray[0]);

            String tag = "";
            if (argArray.length > 1) {
                tag = argArray[1].trim();
            }

            return new TagCommand(taskNum, tag);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Please input a valid task number in digits (e.g. 1).");
        }
    }

    /**
     * Returns a LocalDate object parsed from the input string.
     *
     * @param dateString The string representing the date in the format dd/MM/yyyy.
     * @return The LocalDate object parsed from the input string.
     * @throws ChatBuddyException If the input string is in the wrong format.
     */
    private static LocalDate parseDate(String dateString) throws ChatBuddyException {
        try {
            LocalDate date = LocalDate.parse(dateString, FORMATTER_INPUT_DEADLINE);
            return date;
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Please input the deadline date in the format dd/MM/yyyy.");
        }
    }

    /**
     * Returns a LocalDateTime object parsed from the input string.
     *
     * @param dateTimeString The string representing the datetime in the format dd/MM/yyyy HHmm.
     * @return The LocalDateTime object parsed from the input string.
     * @throws ChatBuddyException If the input string is in the wrong format.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) throws ChatBuddyException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, FORMATTER_INPUT_EVENT);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Please input the datetime in the format dd/MM/yyyy HHmm.");
        }
    }

    /**
     * Returns a Task object parsed from the input string.
     *
     * @param taskString The string representing the task in saved format.
     * @return The Task object parsed from the input string.
     * @throws ChatBuddyException If the input string is in the wrong format.
     */
    public static Task parseToTask(String taskString) throws ChatBuddyException {
        String[] taskData = taskString.split(" \\| ");
        String taskType = taskData[0];
        boolean isCompleted = taskData[1].equals("1");
        String taskDescription = taskData[2];
        String tag = taskData.length <= 3 ? "" : taskData[3];

        // create task object
        Task task;
        if (taskType.equals("T")) {
            task = createToDo(taskDescription);
        } else if (taskType.equals("D")) {
            task = createDeadline(taskDescription, taskData[4]);
        } else if (taskType.equals("E")) {
            task = createEvent(taskDescription, taskData[4], taskData[5]);
        } else {
            throw new ChatBuddyException("Error parsing data from file");
        }

        // update completion status
        if (isCompleted) {
            task.markAsDone();
        }

        task.updateTag(tag);

        return task;
    }

    /**
     * Returns a ToDo object with the given task description.
     *
     * @param taskDescription The description of the task.
     * @return A ToDo object with the given task description.
     */
    private static ToDo createToDo(String taskDescription) {
        return new ToDo(taskDescription);
    }

    /**
     * Returns a Deadline object with the given description and deadline.
     *
     * @param taskDesc The description of the task.
     * @param deadline The string representing the deadline of the task.
     * @return A Deadline object with the given description and deadline.
     * @throws ChatBuddyException If the deadline is not given in the correct format.
     */
    private static Task createDeadline(String taskDesc, String deadline) throws ChatBuddyException {
        LocalDate by = parseDate(deadline);
        return new Deadline(taskDesc, by);
    }

    /**
     * Returns an Event object with the given description, from and to datetime.
     *
     * @param taskDesc The description of the task.
     * @param fromString The string representing the start date and time of the task.
     * @param toString The string representing the end date and time of the task.
     * @return The Event object with the given description, from and to datetime.
     * @throws ChatBuddyException If the from or to date time is not in the correct format.
     */
    private static Task createEvent(String taskDesc, String fromString, String toString) throws ChatBuddyException {
        LocalDateTime from = parseDateTime(fromString);
        LocalDateTime to = parseDateTime(toString);
        return new Event(taskDesc, from, to);
    }
}
