import extensions.exceptions.DukeException;
import extensions.exceptions.DukeIllegalArgumentException;
import extensions.exceptions.DukeUnknownCommandException;
import extensions.tasks.TaskList;
import extensions.tasks.TaskList.TaskType;

import java.util.Scanner;

public class Duke {

    // Error messages
    private static final String ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER =
            "Please enter a valid task number. You entered: \"%s\"";
    private static final String ERROR_MESSAGE_TEMPLATE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what \"%s\" means.";

    // Read user input
    private static final Scanner sc = new Scanner(System.in);
    // Store list of tasks
    private static final TaskList list = new TaskList();
    // Check if chat has ended
    private static boolean hasEndedChat = false;

    /**
     * Ends the chat when the user inputs "bye".
     */
    private static void endChat() {
        hasEndedChat = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the current list of tasks.
     */
    private static void listTasks() {
        System.out.println(list);
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
     * Parses the input.
     *
     * @param input The input from the user.
     * @return A String array where the 1st element is the command and the 2nd element is the argument.
     */
    private static String[] parseCommand(String input) {
        // check if input is a command with arguments (e.g. mark 2)
        String[] splitInputs = input.split("\\s+");

        if (splitInputs.length > 1) {
            // the user has input a command with arguments
            String command = splitInputs[0];
            String argument = input.substring(command.length() + 1);
            return new String[]{command, argument};
        } else {
            // the user has input a command without arguments
            return new String[]{input, ""};
        }
    }

    private static void executeCommand(String[] command) throws DukeException {
        switch (command[0]) {
        case "bye":
            endChat();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            try {
                markTaskAsDone(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, command[1]));
            }
            break;
        case "unmark":
            try {
                unmarkTaskAsDone(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, command[1]));
            }
            break;
        case "todo":
            addToDoTask(command[1]);
            break;
        case "deadline":
            if (!command[1].contains(" /by ")) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a \"/by\" field.");
            }
            String[] deadlineArgs = command[1].split(" /by ");
            if (deadlineArgs.length != 2) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a description and/or date/time.");
            }
            addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (!command[1].contains(" /from ") || !command[1].contains(" /to ")) {
                throw new DukeIllegalArgumentException("Event Task is missing \"/from\" and/or \"/to\" fields.");
            }
            String[] eventArgs = command[1].split(" /from ");
            String[] eventTimes = eventArgs[1].split(" /to ");
            addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
            break;
        case "delete":
            try {
                deleteTask(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE_INVALID_TASK_NUMBER, command[1]));
            }
            break;
        default:
            throw new DukeUnknownCommandException(
                    String.format(ERROR_MESSAGE_TEMPLATE_UNKNOWN_COMMAND, command[0]));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");

        while (!hasEndedChat) {
            String input = sc.nextLine();
            String[] command = parseCommand(input);
            try {
                executeCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
