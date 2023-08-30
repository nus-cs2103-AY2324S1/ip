import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A chatbot that helps you keep track of tasks.
 */
public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
                                                + "What can I do for you?\n";
    private static final String exitMessage = "Bye! Hope to see you again soon!\n";
    private static final String invalidIndexMessage = "Great heavens! You have entered an invalid index!\n";
    private static final String invalidInputMessage = "Great heavens! You have entered an invalid input!\n"
            + "Here are the valid commands:\n"
            + "  bye\n"
            + "  list\n"
            + "  mark|unmark <index>\n"
            + "  delete <index>\n"
            + "  todo <description>\n"
            + "  deadline <description> /by <date>\n"
            + "  event <description> /from <start> /to <end>\n";
    private static final String invalidMarkMessage = "Great heavens! The index of mark cannot be empty!\n"
            + "Usage: mark <index>\n";
    private static final String invalidUnmarkMessage = "Great heavens! The index of unmark cannot be empty!\n"
            + "Usage: unmark <index>\n";
    private static final String invalidTodoMessage = "Great heavens! The description of todo cannot be empty!\n"
            + "Usage: todo <description>\n";
    private static final String invalidDeadlineMessage = "Great heavens! Invalid usage of deadline!\n"
            + "Usage: deadline <description> /by <yyyy-mm-dd HHmm> (24h format)\n";
    private static final String invalidEventMessage = "Great heavens! Invalid usage of event!\n"
            + "Usage: event <description> /from <start> /to <end>\n";
    private static final String invalidDeleteMessage = "Great heavens! The index of delete cannot be empty!\n"
            + "Usage: delete <index>\n";
    private static final String markDoneMessage = "Great success! I have marked this task as done:\n";
    private static final String markUndoneMessage = "Very nice! I have marked this task as not done yet:\n";
    private static final String listMessage = "Here are the tasks in your list:\n";
    private static final String regexPattern = "\\b(bye|list|unmark|mark|todo|deadline|event|delete)\\s*" // match command
            + "([^/]*[^/\\s])?\\s*"                     // match chars that are not / after command, trimming whitespace
            + "(?:(/by|/from)\\s+([^/]*[^/\\s]))?\\s*"  // match /by or /from command and argument, trimming whitespace
            + "(?:(/to)\\s+([^/]*[^/\\s]))?\\s*";       // match /to command and argument, trimming whitespace
    private static final String newTaskAddedMessage = "New task just dropped!\n";
    private static final String taskDeletedMessage = "Task went on vacation, never came back.\n";
    private static final String totalTaskCountMessage = "You now have %d tasks in the list!\n";


    /**
     * Helper method to print a message with horizontal lines above and below it.
     *
     * @param message The message to be printed.
     */
    private static void printWithLines(String message) {
        System.out.print(line + message + line);
    }

    /**
     * Helper method to print all the Tasks in the list with their respective indices,
     * surrounded by horizontal lines.
     *
     * @param list The list containing the tasks.
     */
    private static void printList(ArrayList<Task> list) {
        System.out.print(line + listMessage);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.print(line);
    }

    /**
     * Helper method to print the new task added along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The new task added.
     * @param totalTasks The total count of tasks in the list.
     */
    private static void printTaskAdded(Task task, int totalTasks) {
        System.out.print(line + newTaskAddedMessage);
        System.out.print("  " + task + "\n");
        System.out.printf(totalTaskCountMessage, totalTasks);
        System.out.print(line);
    }

    /**
     * Helper method to print the task deleted along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The deleted task.
     * @param totalTasks The total count of tasks in the list.
     */
    private static void printTaskDeleted(Task task, int totalTasks) {
        System.out.print(line + taskDeletedMessage);
        System.out.print("  " + task + "\n");
        System.out.printf(totalTaskCountMessage, totalTasks);
        System.out.print(line);
    }

    public static void main(String[] args) {
        Storage storage = new Storage("./data/duke.txt");
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile(regexPattern);
        ArrayList<Task> tasks;
        try {
            tasks = storage.loadTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new ArrayList<>();
        }
        printWithLines(greetingMessage);

        while (true) {
            try {
                String str = sc.nextLine();
                Matcher matcher = pattern.matcher(str);
                int index;
                Task newTask;

                if (!matcher.matches()) {
                    // no match means input is not valid
                    throw new DukeException(line + invalidInputMessage + line);
                }

                if (matcher.group(1).equals("bye")) {
                    printWithLines(exitMessage);
                    break;
                }

                // match against the command given
                switch (matcher.group(1)) {
                    case "list":
                        printList(tasks);
                        break;
                    case "mark":
                        if (matcher.group(2) == null) {
                            throw new DukeException(line + invalidMarkMessage + line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(line + invalidIndexMessage + line);
                        }

                        tasks.get(index - 1).markAsDone();
                        printWithLines(markDoneMessage + "  " + tasks.get(index - 1) + '\n');
                        storage.saveTasks(tasks);
                        break;
                    case "unmark":
                        if (matcher.group(2) == null) {
                            throw new DukeException(line + invalidUnmarkMessage + line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(line + invalidIndexMessage + line);
                        }

                        tasks.get(index - 1).markAsUndone();
                        printWithLines(markUndoneMessage + "  " + tasks.get(index - 1) + '\n');
                        storage.saveTasks(tasks);
                        break;
                    case "todo":
                        if (matcher.group(2) == null) {
                            throw new DukeException(line + invalidTodoMessage + line);
                        }

                        newTask = new Todo(matcher.group(2));
                        tasks.add(newTask);
                        printTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "deadline":
                        if (matcher.group(2) == null
                                || matcher.group(3) == null
                                || !matcher.group(3).equals("/by")
                                || matcher.group(4) == null) {
                            throw new DukeException(line + invalidDeadlineMessage + line);
                        }

                        LocalDateTime parsedDate;
                        try {
                            parsedDate = LocalDateTime.parse(matcher.group(4),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        } catch (DateTimeParseException e) {
                            throw new DukeException(line + invalidDeadlineMessage + line);
                        }

                        newTask = new Deadline(matcher.group(2), parsedDate);
                        tasks.add(newTask);
                        printTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "event":
                        if (matcher.group(2) == null
                                || matcher.group(3) == null
                                || !matcher.group(3).equals("/from")
                                || matcher.group(4) == null
                                || matcher.group(5) == null
                                || !matcher.group(5).equals("/to")
                                || matcher.group(6) == null) {
                            throw new DukeException(line + invalidEventMessage + line);
                        }

                        newTask = new Event(matcher.group(2), matcher.group(4), matcher.group(6));
                        tasks.add(newTask);
                        printTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "delete":
                        if (matcher.group(2) == null) {
                            throw new DukeException(line + invalidDeleteMessage + line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(line + invalidIndexMessage + line);
                        }

                        Task removed = tasks.remove(index - 1);
                        printTaskDeleted(removed, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    default:
                        throw new DukeException(line + invalidInputMessage + line);
                }
            } catch (DukeException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
