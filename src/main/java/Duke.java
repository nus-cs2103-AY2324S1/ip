import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String NAME = "Buzu";
    private static final String DIVIDER = "__________________________________________________";
    private static final String INDENT = "    ";
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Utility function to add indentation when printing line.
     *
     * @param line The line to be indented
     */
    private static void printLine(String line) {
        System.out.println(INDENT + line);
    }

    /**
     * Utility function for chatbot to send a formatted response to the user.
     *
     * @param text The text to respond with
     */
    private static void say(String... text) {
        printLine(DIVIDER);
        for (String line : text) {
            printLine(line);
        }
        printLine(DIVIDER);
        System.out.println();
    }

    /**
     * Utility function to return the command type of a given command string.
     *
     * @param command The command string to parse
     * @return The Command type of the parsed string
     */
    private static Command getCommandType(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Command.INVALID;
        }
    }

    /**
     * Handler for the todo command. Generates the required response.
     *
     * @param input The parameters for the todo command
     * @return The response string to the user
     */
    private static String[] handleTodo(String input) {
        String[] response = new String[3];
        Todo task = new Todo(input);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the deadline command. Generates the required response.
     *
     * @param input The parameters for the deadline command
     * @return The response string to the user
     * @throws DukeException if input for deadline is missing the "/by" keyword
     */
    private static String[] handleDeadline(String input) throws DukeException {
        String[] response = new String[3];
        String[] tokens = input.split(" /by ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Deadline is missing the /by keyword");
        }

        Deadline task = new Deadline(tokens[0], tokens[1]);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the event command. Generates the required response.
     *
     * @param input The parameters for the event command
     * @return The response string to the user
     * @throws DukeException if input for event is missing the "/from" or "/to" keyword
     */
    private static String[] handleEvent(String input) throws DukeException {
        String[] response = new String[3];
        String[] tokens = input.split(" /from ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Event is missing the /from keyword");
        }
        String[] tokens2 = tokens[1].split(" /to ", 2);
        if (tokens2.length == 1) {
            throw new DukeException("Event is missing the /to keyword");
        }

        Event task = new Event(tokens[0], tokens2[0], tokens2[1]);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the list command. Generates the required response.
     *
     * @return The response string to the user
     */
    private static String[] handleList() {
        String[] response;
        int numTasks = tasks.size();
        if (numTasks == 0) {
            response = new String[1];
            response[0] = "There are no items in your list.";
        } else {
            response = new String[numTasks + 1];
            response[0] = "Here are the tasks in your list:";
            for (int i = 0; i < numTasks; i++) {
                response[i + 1] = (i + 1 + ". " + tasks.get(i));
            }
        }
        return response;
    }

    /**
     * Handler for the mark command. Generates the required response.
     *
     * @param input The parameters for the mark command
     * @return The response string to the user
     * @throws DukeException if input for mark is not an integer
     */
    private static String[] handleMark(String input) throws DukeException {
        String[] response = new String[2];
        int taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException err) {
            throw new DukeException("Mark can only mark an integer task number");
        }
        Task task = tasks.get(taskNum - 1);
        task.mark();

        response[0] = "Nice! I've marked this task as done:";
        response[1] = INDENT + task;
        return response;
    }

    /**
     * Handler for the unmark command. Generates the required response.
     *
     * @param input The parameters for the unmark command
     * @return The response string to the user
     * @throws DukeException if input for unmark is not an integer
     */
    private static String[] handleUnmark(String input) throws DukeException {
        String[] response = new String[2];
        int taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException err) {
            throw new DukeException("Unmark can only unmark an integer task number");
        }
        Task task = tasks.get(taskNum - 1);
        task.unmark();

        response[0] = "Ok, I've marked this task as not done yet:";
        response[1] = INDENT + task;
        return response;
    }

    public static void main(String[] args) {
        say("Hello! I'm " + NAME + ".", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean stopped = false;

        while (!stopped && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] params = input.split(" ", 2);
            Command command = getCommandType(params[0]);

            switch (command) {
                case TODO: {
                    if (params.length == 1) {
                        say("Sorry, but the description of a todo cannot be empty.");
                    } else {
                        String[] response = handleTodo(params[1]);
                        say(response);
                    }
                    break;
                }
                case DEADLINE: {
                    if (params.length == 1) {
                        say("Sorry, but the description of a deadline cannot be empty.");
                    } else {
                        try {
                            String[] response = handleDeadline(params[1]);
                            say(response);
                        } catch (DukeException err) {
                            say(err.getMessage() + ".");
                        }
                    }
                    break;
                }
                case EVENT: {
                    if (params.length == 1) {
                        say("Sorry, but the description of a event cannot be empty.");
                    } else {
                        try {
                            String[] response = handleEvent(params[1]);
                            say(response);
                        } catch (DukeException err) {
                            say(err.getMessage() + ".");
                        }
                    }
                    break;
                }
                case LIST: {
                    String[] response = handleList();
                    say(response);
                    break;
                }
                case MARK: {
                    try {
                        String[] response = handleMark(params[1]);
                        say(response);
                    } catch (DukeException err) {
                        say(err.getMessage() + ".");
                    }
                    break;
                }
                case UNMARK: {
                    try {
                        String[] response = handleUnmark(params[1]);
                        say(response);
                    } catch (DukeException err) {
                        say(err.getMessage() + ".");
                    }
                    break;
                }
                case BYE:
                    stopped = true;
                    say("Bye! Hope to see you again soon!");
                    break;
                default:
                    say("Sorry, but I don't know what that means :(");
                    break;
            }
        }
    }
}
