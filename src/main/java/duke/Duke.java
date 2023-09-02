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
        return "Bye. Hope to see you again soon!";
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
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @return String containing the task that was marked as done.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static String markTaskAsDone(int num) throws DukeIllegalArgumentException {
        return list.mark(num);
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @return String containing the task that was marked as undone.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static String unmarkTaskAsDone(int num) throws DukeIllegalArgumentException {
        return list.unmark(num);
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
     * Deletes a task from the list.
     *
     * @param num The number of the task to be deleted.
     * @return String containing the task that was deleted.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static String deleteTask(int num) throws DukeIllegalArgumentException {
        return list.delete(num);
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
            try {
                output = markTaskAsDone(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case UNMARK:
            try {
                output = unmarkTaskAsDone(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case TODO:
            output = addToDoTask(argument);
            break;
        case DEADLINE:
            if (!argument.contains(" /by ")) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a \"/by\" field.");
            }
            String[] deadlineArgs = argument.split(" /by ");
            if (deadlineArgs.length != 2) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a description and/or date/time.");
            }
            output = addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
            break;
        case EVENT:
            if (!argument.contains(" /from ") || !argument.contains(" /to ")) {
                throw new DukeIllegalArgumentException("Event Task is missing \"/from\" and/or \"/to\" fields.");
            }
            String[] eventArgs = argument.split(" /from ");
            String[] eventTimes = eventArgs[1].split(" /to ");
            output = addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
            break;
        case DELETE:
            try {
                output = deleteTask(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case FIND:
            if (argument.isBlank()) {
                throw new DukeIllegalArgumentException("Find command is missing a search term.");
            } else {
                output = (list.find(argument));
            }
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
     * Entrypoint to launch the Duke GUI.
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
                return String.format("[ERROR] %s", e.getMessage());
            }
        } else {
            return "Max has left the chat!";
        }
    }

    /**
     * Returns Duke's greeting.
     *
     * @return String containing Duke's greeting.
     */
    public String greet() {
        return "Hello! I'm Max!\nWhat can I do for you?";
    }

}
