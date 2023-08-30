import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalArgumentException;
import duke.exceptions.DukeUnknownCommandException;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.tasks.TaskList.TaskType;
import duke.ui.Ui;

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
     */
    private static void endChat() {
        hasEndedChat = true;
        Ui.printGoodbyeMessage();
    }

    /**
     * Prints the current list of tasks.
     */
    private static void listTasks() {
        Ui.printMessage(list.toString());
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void markTaskAsDone(int num) throws DukeIllegalArgumentException {
        list.mark(num);
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void unmarkTaskAsDone(int num) throws DukeIllegalArgumentException {
        list.unmark(num);
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     */
    private static void addToDoTask(String description) throws DukeIllegalArgumentException {
        list.add(TaskType.TODO, description);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by The date/time of the Deadline task.
     */
    private static void addDeadlineTask(String description, String by) throws DukeIllegalArgumentException {
        list.add(TaskType.DEADLINE, description, by);
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event task.
     * @param end The end date/time of the Event task.
     */
    private static void addEventTask(String description, String start, String end)
            throws DukeIllegalArgumentException {
        list.add(TaskType.EVENT, description, start, end);
    }

    /**
     * Deletes a task from the list.
     *
     * @param num The number of the task to be deleted.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void deleteTask(int num) throws DukeIllegalArgumentException {
        list.delete(num);
    }

    /**
     * Executes the parsed command given by the user.
     *
     * @param parsedCommand a Parser Object containing the parsed command and arguments.
     * @throws DukeException If the command is invalid.
     */
    private static void executeCommand(Parser parsedCommand) throws DukeException {
        Parser.Command command = parsedCommand.getCommand();
        String argument = parsedCommand.getArgument();
        switch (command) {
        case BYE:
            endChat();
            break;
        case LIST:
            listTasks();
            break;
        case MARK:
            try {
                markTaskAsDone(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case UNMARK:
            try {
                unmarkTaskAsDone(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case TODO:
            addToDoTask(argument);
            break;
        case DEADLINE:
            if (!argument.contains(" /by ")) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a \"/by\" field.");
            }
            String[] deadlineArgs = argument.split(" /by ");
            if (deadlineArgs.length != 2) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a description and/or date/time.");
            }
            addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
            break;
        case EVENT:
            if (!argument.contains(" /from ") || !argument.contains(" /to ")) {
                throw new DukeIllegalArgumentException("Event Task is missing \"/from\" and/or \"/to\" fields.");
            }
            String[] eventArgs = argument.split(" /from ");
            String[] eventTimes = eventArgs[1].split(" /to ");
            addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
            break;
        case DELETE:
            try {
                deleteTask(Integer.parseInt(argument));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, argument));
            }
            break;
        case FIND:
            if (argument.isBlank()) {
                throw new DukeIllegalArgumentException("Find command is missing a search term.");
            } else {
                Ui.printMessage(list.find(argument));
            }
            break;
        case INVALID:
            throw new DukeUnknownCommandException(String.format(
                    ERROR_MESSAGE_TEMPLATE_UNKNOWN_COMMAND, parsedCommand.getInvalidCommand()));
        default:
            break;
        }
    }

    /**
     * Main entrypoint for Duke.
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();

        while (!hasEndedChat) {
            String input = Ui.getInput();
            Parser parsedCommand = new Parser(input);
            try {
                executeCommand(parsedCommand);
            } catch (DukeException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}
