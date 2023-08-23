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
    /**
     * Decides which command to give to the bot based on user input.
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

            switch (command) {
                case "mark":
                    return Command.MARK;
                case "unmark":
                    return Command.UNMARK;
                case "delete":
                    return Command.DELETE;
                case "todo":
                    return Command.TODO;
                case "deadline":
                    return Command.DEADLINE;
                case "event":
                    return Command.EVENT;
                default:
                    return Command.INVALID;
            }
        }
    }

    /**
     * Adds given task to given list.
     * @param list List to add task to.
     * @param task Task to be added.
     */
    public static void addTask(List<Task> list, Task task) {
        list.add(task);
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tGot it bro! I've added this task:\n\t\t" + task);
        System.out.println("\tNow you have " + list.size() + " tasks in the list");
        System.out.println("\t-----------------------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (true) {
            // keep prompting user until user enters "bye"
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String taskType = words[0];
            Command command = decideCommand(input);

            try {
                switch (command) {
                    case BYE:
                        // exit program
                        System.out.println("\t-----------------------------------------------");
                        System.out.println("\tBye. Hope to see you again soon bro!");
                        System.out.println("\t-----------------------------------------------");
                        return;

                    case LIST:
                        // displays items in current list
                        System.out.println("\t-----------------------------------------------");
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("\t" + (i + 1) + "." + list.get(i));
                        }
                        System.out.println("\t-----------------------------------------------");
                        break;

                    case MARK:
                        // mark task as done
                        try {
                            int index = Integer.parseInt(words[1]) - 1;
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
                        break;

                    case UNMARK:
                        // mark task as undone
                        try {
                            int index = Integer.parseInt(words[1]) - 1;
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
                        break;

                    case DELETE:
                        // delete task
                        try {
                            int index = Integer.parseInt(words[1]) - 1;
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
                        break;

                    case TODO:
                        String description;
                        Task task;
                        // Define regular expressions for pattern matching for todo
                        Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

                        // Match the input string with the pattern
                        Matcher matcher = todoPattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract task description
                            task = new Todo(description);
                            addTask(list, task);
                        } else {
                            // Todo description is empty
                            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty."
                                    + "\n\ttodo ...");
                        }
                        break;

                    case EVENT:
                        // Define regular expressions for pattern matching for event
                        Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from" +
                                "\\s+(.*?)\\s+/to\\s+(.*?)$");

                        // Match the input string with the pattern
                        matcher = eventPattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract event name
                            String startTime = matcher.group(2); // Extract start time
                            String endTime = matcher.group(3);   // Extract end time
                            task = new Event(startTime, endTime, description);
                            addTask(list, task);
                        } else {
                            // User did not follow event format
                            throw new DukeException("\tInput for event doesn't match the expected format."
                                    + "\n\tevent ... /from ... /to ...");
                        }
                        break;

                    case DEADLINE:
                        // Define regular expressions for pattern matching for deadline
                        Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

                        // Match the input string with the pattern
                        matcher = deadlinePattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract task description
                            String dueDate = matcher.group(2);  // Extract due date
                            task = new Deadline(dueDate, description);
                            addTask(list, task);
                        } else {
                            // User did not follow deadline format
                            throw new DukeException("\tInput for deadline doesn't match the expected format."
                                    + "\n\tdeadline ... /by ...");
                        }
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
}
