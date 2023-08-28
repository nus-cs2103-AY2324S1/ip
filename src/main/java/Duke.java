import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    INVALID
}


/**
 * Contains the chatbot Brobot. It allows users to add and delete different types of tasks and mark them
 * as complete or incomplete
 */
public class Duke {
    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // keep prompting user until user enters "bye"
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            Command command = decideCommand(input);

            try {
                switch (command) {
                case BYE:
                    // exit program
                    printExitMessage();
                    return;
                case LIST:
                    printList();
                    break;
                case MARK:
                    markTask(words[1]);
                    break;
                case UNMARK:
                    unmarkTask(words[1]);
                    break;
                case DELETE:
                    deleteTask(words[1]);
                    break;
                case TODO:
                    addTodo(input);
                    break;
                case EVENT:
                    addEvent(input);
                    break;
                case DEADLINE:
                    addDeadline(input);
                    break;
                default:
                    // invalid input
                    throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                System.out.println("\t-----------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("\t-----------------------------------------------");
            }
        }
    }

    /**
     * Prints a welcome greeting for the user.
     */
    public static void printGreetings() {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
    }

    /**
     * Decides which command to give to the bot based on user input.
     *
     * @param input The String input by the user.
     * @return The command to be used by the bot.
     */
    public static Command decideCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals(("list"))) {
            return Command.LIST;
        } else {
            String[] words = input.split(" ");
            String command = words[0];

            for (Command c : Command.values()) {
                if (c.name().toLowerCase().equals(command)) {
                    return c;
                }
            }

            return Command.INVALID;
        }
    }

    /**
     * Adds given task to given list.
     *
     * @param task Task to be added.
     */
    public static void addTask(Task task) {
        list.add(task);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tGot it bro! I've added this task:\n\t\t" + task);
        System.out.println("\tNow you have " + list.size() + " tasks in the list");
        System.out.println("\t-----------------------------------------------");
    }

    /**
     * Bids farewell to the user.
     */
    public static void printExitMessage() {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon bro!");
        System.out.println("\t-----------------------------------------------");
    }

    /**
     * Prints all tasks in the list currently.
     */
    public static void printList() {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
        System.out.println("\t-----------------------------------------------");
    }

    /**
     * Extracts task index from user input and marks the task as done.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public static void markTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).markAsDone();
            System.out.println("\t-----------------------------------------------");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + list.get(index));
            System.out.println("\t-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("\tInvalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("\tPlease key in a number");
        }
    }

    /**
     * Extracts task index from user input and marks the task as not done.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public static void unmarkTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).unMark();
            System.out.println("\t-----------------------------------------------");
            System.out.println("\tNice! I've marked this task as not done yet:");
            System.out.println("\t" + list.get(index));
            System.out.println("\t-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("\tInvalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("\tPlease key in a number");
        }
    }

    /**
     * Extracts task index from user input and deletes the task.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public static void deleteTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index);
            System.out.println("\t-----------------------------------------------");
            System.out.println("\tNoted! I've removed this task from the list:");
            System.out.println("\t\t" + list.get(index));
            list.remove(index);
            System.out.println("\tNow you have " + list.size() + " tasks in the list");
            System.out.println("\t-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("\tInvalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("\tPlease key in a number");
        }
    }

    /**
     * Extracts task description from user input and adds a todo task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public static void addTodo(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract task description
            Task task = new Todo(description);
            addTask(task);
        } else {
            // Todo description is empty
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty."
                    + "\n\ttodo ...");
        }
    }

    /**
     * Extracts task description, start time and end time from user input and adds an event task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public static void addEvent(String input) throws DukeException {
        // Define regular expressions for pattern matching for event
        Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from" +
                "\\s+(.*?)\\s+/to\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = eventPattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract event name
            String startTime = matcher.group(2); // Extract start time
            String endTime = matcher.group(3);   // Extract end time
            Task task = new Event(startTime, endTime, description);
            addTask(task);
        } else {
            // User did not follow event format
            throw new DukeException("\tInput for event doesn't match the expected format."
                    + "\n\tevent ... /from ... /to ...");
        }
    }

    /**
     * Extracts task description and due date from user input and adds a deadline task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public static void addDeadline(String input) throws DukeException {
        // Define regular expressions for pattern matching for deadline
        Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = deadlinePattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract task description
            String dueDate = matcher.group(2);  // Extract due date
            Task task = new Deadline(dueDate, description);
            addTask(task);
        } else {
            // User did not follow deadline format
            throw new DukeException("\tInput for deadline doesn't match the expected format."
                    + "\n\tdeadline ... /by ...");
        }
    }


}
