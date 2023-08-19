import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Duke is a simple task management chatbot that allows users to manager their tasks.
 */
public class Duke {
    private static final Task[] tasks = new Task[100];
    private static int i = 0;

    /**
     * Prints a horizontal line for formatting purposes.
     */
    public static void printHorizontalLine() {
        System.out.println("\t------------------------------------------------------------------------------------");
    }

    /**
     * Prints a message from the bot. It will be indented.
     * @param msg The message to be displayed.
     */
    public static void printBotMessage(String msg) {
        printHorizontalLine();
        System.out.println("\t" + msg);
        printHorizontalLine();
    }

    /**
     * Prints a greeting message from the bot.
     */
    public static void printGreeting() {
        String logo = "\n" +
                "\t_________ .__            __    __                \n" +
                "\t\\_   ___ \\|  |__ _____ _/  |__/  |_  ___________ \n" +
                "\t/    \\  \\/|  |  \\\\__  \\\\   __\\   __\\/ __ \\_  __ \\\n" +
                "\t\\     \\___|   Y  \\/ __ \\|  |  |  | \\  ___/|  | \\/\n" +
                "\t \\______  /___|  (____  /__|  |__|  \\___  >__|   \n" +
                "\t        \\/     \\/     \\/                \\/       \n";
        printBotMessage("Hello! I'm" + logo + "\n\t \uD83E\uDD9C What can I do for you?");
    }

    /**
     * Prints a farewell message from the bot.
     */
    public static void printFarewell() {
        printBotMessage("Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D");
    }

    /**
     * Prints all the tasks in the list.
     */
    public static void printTasks() {
        printHorizontalLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < 100; i++) {
            if (tasks[i] != null) {
                System.out.println("\t" + (i + 1) + ". " + tasks[i]);
            } else {
                break;
            }
        }
        printHorizontalLine();
    }

    /**
     * Marks a task as done.
     * @param taskNumber The task number of the task to be marked as not done.
     */
    public static void unmarkTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber);
        tasks[index - 1].markAsUndone();
        printBotMessage("OK, I've marked this task as not done yet:\n\t\t" + tasks[index - 1]);
    }

    /**
     * Marks a task as not done.
     * @param taskNumber The task number of the task to be marked as done.
     */
    public static void markTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber);
        tasks[index - 1].markAsDone();
        printBotMessage("Nice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
    }

    /**
     * Adds a todo task to the list.
     * @param inputList The input list containing the description of the todo task.
     */
    public static void addTodo(List<String> inputList) {
        String description = String.join(" ", inputList.subList(1, inputList.size()));
        Todo todo = new Todo(description);
        tasks[i] = todo;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + todo +
                "\n\tNow you have " + i + " tasks in the list.");
    }

    /**
     * Adds a deadline task to the list.
     * @param inputList The input list containing the description and deadline of the deadline task.
     */
    public static void addDeadline(List<String> inputList) {
        String input = String.join(" ", inputList.subList(1, inputList.size()));
        String[] split = input.split(" /by ");
        Deadline deadline = new Deadline(split[0], split[1]);
        tasks[i] = deadline;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + deadline +
                "\n\tNow you have " + i + " tasks in the list.");
    }

    /**
     * Adds an event task to the list.
     * @param inputList The input list containing the description and start and end time of the event task.
     */
    public static void addEvent(List<String> inputList) {
        String input = String.join(" ", inputList.subList(1, inputList.size()));
        String[] split = input.split(" /from ");
        String[] split2 = split[1].split(" /to ");
        Event event = new Event(split[0], split2[0], split2[1]);
        tasks[i] = event;
        i++;
        printBotMessage("Got it. I've added this task:\n\t\t" + event +
                "\n\tNow you have " + i + " tasks in the list.");
    }
    public static void main(String[] args) {
        printGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            String keyword = inputList.get(0);

            switch (keyword) {
                case "bye":
                    printFarewell();
                    return;
                case "list":
                    printTasks();
                    break;
                case "unmark":
                    unmarkTask(String.join(" ", inputList.subList(1, inputList.size())));
                    break;
                case "mark":
                    markTask(String.join(" ", inputList.subList(1, inputList.size())));
                    break;
                case "todo":
                    addTodo(inputList);
                    break;
                case "deadline":
                    addDeadline(inputList);
                    break;
                case "event":
                    addEvent(inputList);
                    break;
                default:
                    printBotMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }

    }
}
