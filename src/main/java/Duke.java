import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String NAME = "Buzu";
    private static final String DIVIDER = "__________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Utility function to add indentation when printing line.
     *
     * @param line The line to be indented
     */
    private static void printLine(String line) {
        System.out.println("\t" + line);
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
     * Utility function to add a task to the list and respond to the user.
     *
     * @param task The task to be added
     */
    private static void addTask(Task task) {
        tasks.add(task);
        say("Got it. I've added this task:",
                "\t" + task.toString(),
                String.format("Now you have %d task%s in your list.", tasks.size(), tasks.size() == 1 ? "" : "s"));
    }

    private static void handleTodo(String input) {
        Todo task = new Todo(input);
        addTask(task);
    }

    private static void handleDeadline(String input) {
        String[] tokens = input.split(" /by ", 2);
        Deadline task = new Deadline(tokens[0], tokens[1]);
        addTask(task);
    }

    private static void handleEvent(String input) {
        String[] tokens = input.split(" /from ", 2);
        String[] tokens2 = tokens[1].split(" /to ", 2);
        Event task = new Event(tokens[0], tokens2[0], tokens2[1]);
        addTask(task);
    }

    private static void handleList() {
        int numTasks = tasks.size();
        if (numTasks == 0) {
            say("There are no items in your list.");
        } else {
            String[] response = new String[numTasks + 1];
            response[0] = "Here are the tasks in your list:";
            for (int i = 0; i < numTasks; i++) {
                response[i + 1] = (i + 1 + ". " + tasks.get(i));
            }
            say(response);
        }
    }

    private static void handleMark(String input) {
        int taskNum = Integer.parseInt(input);
        Task task = tasks.get(taskNum - 1);
        task.mark();
        say("Nice! I've marked this task as done:", "\t" + task);
    }

    private static void handleUnmark(String input) {
        int taskNum = Integer.parseInt(input);
        Task task = tasks.get(taskNum - 1);
        task.unmark();
        say("Ok, I've marked this task as not done yet:", "\t" + task);
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
                case TODO:
                    handleTodo(params[1]);
                    break;
                case DEADLINE:
                    handleDeadline(params[1]);
                    break;
                case EVENT:
                    handleEvent(params[1]);
                    break;
                case LIST:
                    handleList();
                    break;
                case MARK:
                    handleMark(params[1]);
                    break;
                case UNMARK:
                    handleUnmark(params[1]);
                    break;
                case BYE:
                    stopped = true;
                    say("Bye! Hope to see you again soon!");
                    break;
                default:
                    say("That is an invalid command.");
                    break;
            }
        }
    }
}
