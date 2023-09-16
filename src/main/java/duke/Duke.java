package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalArgumentException;
import duke.exceptions.DukeUnknownCommandException;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.tasks.TaskList.TaskType;
import duke.ui.Launcher;

/**
 * Duke, the chatbot.
 */
public class Duke {

    // Error messages
    private static final String ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER =
            "Please enter a valid task number. You entered: \"%s\"";
    private static final String ERROR_MESSAGE_TEMPLATE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what \"%s\" means.";
    private static final String ERROR_MESSAGE_DEADLINE_MISSING_DEADLINE =
            "Deadline Task is missing a \"/by\" field.";
    private static final String ERROR_MESSAGE_DEADLINE_MISSING_PARAMS =
            "Deadline Task is missing a description and/or date/time.";
    private static final String ERROR_MESSAGE_EVENT_MISSING_START_END =
            "Event Task is missing \"/from\" and/or \"/to\" fields.";
    private static final String ERROR_MESSAGE_FIND_MISSING_SEARCH_TERM =
            "Find command is missing a search term.";
    private static final String ERROR_MESSAGE_EDIT_MISSING_PARAMS =
            "Edit command is missing a task number and/or fields to update.";
    private static final String ERROR_MESSAGE_TEMPLATE_EXCEPTIONS =
            "[ERROR] %s%nType `help` for a list of commands.";

    // Messages
    private static final String MESSAGE_ALREADY_ENDED_CHAT = "Max has left the chat!";
    private static final String MESSAGE_GREET_USER =
            "Hello! I'm Max!%nWhat can I do for you?%nType `help` (without backticks) to get started.";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_TEMPLATE_HELP_DATETIME_FORMAT =
            "The date/time format must be yyyy-MM-dd HH:mm.%n";
    private static final String MESSAGE_TEMPLATE_HELP_MESSAGE =
        "Here are the commands supported by Max:%n"
        + "`bye` - Ends the chat.%n%n"
        + "`list` - Shows you all your tasks.%n%n"
        + "`mark <task number>` - Marks the task as completed.%n%n"
        + "`unmark <task number>` - Marks the task as incomplete.%n%n"
        + "`todo <description>` - Creates a new ToDo task with description.%n%n"
        + "`deadline <description> /by <date/time>` - Creates a new Deadline task with description and deadline.%n"
        + MESSAGE_TEMPLATE_HELP_DATETIME_FORMAT
        + "%n`event <description> /from <start date/time> /to <end date/time>` - Creates a new Event task with "
        + "description, start date/time and end date/time.%n"
        + MESSAGE_TEMPLATE_HELP_DATETIME_FORMAT
        + "%n`delete <task number>` - Removes the task from your list.%n%n"
        + "`find <search term>` - Find tasks in your list that contains the search term in the description.%n%n"
        + "`edit <task number> [/d <new description>] [/b <deadline date/time>] [/s <start date/time>] "
        + "[/e <end date/time>]` - Updates the corresponding fields in the specified task.%n"
        + MESSAGE_TEMPLATE_HELP_DATETIME_FORMAT
        + "You can specify any number of fields to update and in any order.%n%n"
        + "`help` - Shows this help message.";


    // Store list of tasks
    private static final TaskList list = new TaskList("tasks.txt");

    // Check if chat has ended
    private static boolean hasEndedChat = false;

    /**
     * Ends the chat when the user inputs "bye".
     *
     * @return String containing the goodbye message.
     */
    private static String endChat() {
        hasEndedChat = true;
        return MESSAGE_GOODBYE;
    }

    /**
     * Prints the current list of tasks.
     *
     * @return String containing the current list of tasks.
     */
    private static String listTasks() {
        return list.toString();
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     * @return String containing the ToDo task that was added.
     */
    private static String addToDoTask(String description) throws DukeIllegalArgumentException {
        return list.add(TaskType.TODO, description);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by The date/time of the Deadline task.
     * @return String containing the Deadline task that was added.
     */
    private static String addDeadlineTask(String description, String by) throws DukeIllegalArgumentException {
        return list.add(TaskType.DEADLINE, description, by);
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event task.
     * @param end The end date/time of the Event task.
     * @return String containing the Event task that was added.
     */
    private static String addEventTask(String description, String start, String end)
            throws DukeIllegalArgumentException {
        return list.add(TaskType.EVENT, description, start, end);
    }

    /**
     * Executes the parsed command given by the user.
     *
     * @param parsedCommand a Parser Object containing the parsed command and arguments.
     * @return String containing Duke's response to the command.
     * @throws DukeException If the command is invalid.
     */
    private static String executeCommand(Parser parsedCommand) throws DukeException {
        Parser.Command command = parsedCommand.getCommand();
        String argument = parsedCommand.getArgument();

        // Avoid early return statements in switch statement.
        String output = "";

        switch (command) {
        case BYE:
            output = endChat();
            break;
        case LIST:
            output = listTasks();
            break;
        case MARK:
            output = executeMarkTask(argument);
            break;
        case UNMARK:
            output = executeUnmarkTask(argument);
            break;
        case TODO:
            output = addToDoTask(argument);
            break;
        case DEADLINE:
            output = executeAddDeadlineTask(argument);
            break;
        case EVENT:
            output = executeAddEventTask(argument);
            break;
        case DELETE:
            output = executeDeleteTask(argument);
            break;
        case FIND:
            output = executeFindTask(argument);
            break;
        case EDIT:
            output = executeEditTask(argument);
            break;
        case HELP:
            output = String.format(MESSAGE_TEMPLATE_HELP_MESSAGE);
            break;
        case INVALID:
            throw new DukeUnknownCommandException(String.format(
                    ERROR_MESSAGE_TEMPLATE_UNKNOWN_COMMAND, parsedCommand.getInvalidCommand()));
        default:
            break;
        }
        return output;
    }

    /**
     * Attempts to mark a task as done.
     *
     * @param argument The task number, as a String.
     * @return Output of the mark command.
     * @throws DukeIllegalArgumentException If the task number is not an integer.
     */
    private static String executeMarkTask(String argument) throws DukeIllegalArgumentException {
        String output;
        try {
            output = list.mark(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
        }
        return output;
    }

    /**
     * Attempts to mark a task as done.
     *
     * @param argument The task number, as a String.
     * @return Output of the unmark command.
     * @throws DukeIllegalArgumentException If the task number is not an integer.
     */
    private static String executeUnmarkTask(String argument) throws DukeIllegalArgumentException {
        String output;
        try {
            output = list.unmark(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
        }
        return output;
    }

    /**
     * Attempts to add a Deadline task.
     *
     * @param argument The description and date/time of the Deadline task.
     * @return Output of the add Deadline command.
     * @throws DukeIllegalArgumentException If the argument has missing parameters.
     */
    private static String executeAddDeadlineTask(String argument) throws DukeIllegalArgumentException {
        String output;
        if (!argument.contains(" /by ")) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_DEADLINE_MISSING_DEADLINE);
        }
        String[] deadlineArgs = argument.split(" /by ");
        if (deadlineArgs.length != 2) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_DEADLINE_MISSING_PARAMS);
        }
        output = addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
        return output;
    }

    /**
     * Attempts to add an Event task.
     *
     * @param argument The description, start date/time and end date/time of the Event task.
     * @return Output of the add Event command.
     * @throws DukeIllegalArgumentException If the argument has missing parameters.
     */
    private static String executeAddEventTask(String argument) throws DukeIllegalArgumentException {
        String output;
        if (!argument.contains(" /from ") || !argument.contains(" /to ")) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_EVENT_MISSING_START_END);
        }
        String[] eventArgs = argument.split(" /from ");
        String[] eventTimes = eventArgs[1].split(" /to ");
        output = addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
        return output;
    }

    /**
     * Attempts to delete a task.
     *
     * @param argument The task number, as a String.
     * @return Output of the delete command.
     * @throws DukeIllegalArgumentException If the task number is not an integer.
     */
    private static String executeDeleteTask(String argument) throws DukeIllegalArgumentException {
        String output;
        try {
            output = list.delete(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
        }
        return output;
    }

    /**
     * Attempts to find a task.
     *
     * @param argument The search term.
     * @return Output of the find command.
     * @throws DukeIllegalArgumentException If the search term is missing.
     */
    private static String executeFindTask(String argument) throws DukeIllegalArgumentException {
        String output;
        if (argument.isBlank()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_FIND_MISSING_SEARCH_TERM);
        } else {
            output = list.find(argument);
        }
        return output;
    }

    /**
     * Attempts to edit a task.
     */
    private static String executeEditTask(String argument) throws DukeIllegalArgumentException {
        String output;
        if (argument.isBlank()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_EDIT_MISSING_PARAMS);
        } else {
            String[] splitArgs = argument.split(" ", 2);
            if (splitArgs.length != 2) {
                throw new DukeIllegalArgumentException(ERROR_MESSAGE_EDIT_MISSING_PARAMS);
            }
            try {
                output = list.edit(Integer.parseInt(splitArgs[0]), splitArgs[1]);
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
        }
        return output;
    }

    /**
     * Launches the Duke GUI.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Returns Duke's response to the input.
     *
     * @param input The input from the user.
     * @return String containing Duke's response to the input.
     */
    public String getResponse(String input) {
        if (!hasEndedChat) {
            Parser parsedCommand = new Parser(input);
            try {
                return executeCommand(parsedCommand);
            } catch (DukeException e) {
                return String.format(ERROR_MESSAGE_TEMPLATE_EXCEPTIONS, e.getMessage());
            }
        } else {
            return MESSAGE_ALREADY_ENDED_CHAT;
        }
    }

    /**
     * Returns Duke's greeting.
     *
     * @return String containing Duke's greeting.
     */
    public String greet() {
        return String.format(MESSAGE_GREET_USER);
    }

}
