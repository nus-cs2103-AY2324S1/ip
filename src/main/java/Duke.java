import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String NAME = "Buzu";
    private static final String DIVIDER = "__________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();

    enum Command {
        ADD,
        LIST,
        MARK,
        UNMARK,
        BYE
    }

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
            return Command.ADD;
        }
    }

    private static void handleMark(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.mark();
        say("Nice! I've marked this task as done:", "\t" + task.toString());
    }

    private static void handleUnmark(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.unmark();
        say("Ok, I've marked this task as not done yet:", "\t" + task.toString());
    }

    public static void main(String[] args) {
        say("Hello! I'm " + NAME + ".", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean stopped = false;

        while (!stopped && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            Command command = getCommandType(tokens[0]);

            switch (command) {
                case ADD:
                    tasks.add(new Task(input));
                    say("added: " + input);
                    break;
                case LIST:
                    int numItems = tasks.size();
                    if (numItems == 0) {
                        say("There are no items in your list.");
                    } else {
                        String[] response = new String[numItems];
                        for (int i = 0; i < numItems; i++) {
                            response[i] = (i + 1 + ". " + tasks.get(i));
                        }
                        say("Here are the tasks in your list:");
                        say(response);
                    }
                    break;
                case MARK:
                    handleMark(Integer.parseInt(tokens[1]));
                    break;
                case UNMARK:
                    handleUnmark(Integer.parseInt(tokens[1]));
                    break;
                case BYE:
                    stopped = true;
                    say("Bye! Hope to see you again soon!");
                    break;
                default:
            }
        }
    }
}
