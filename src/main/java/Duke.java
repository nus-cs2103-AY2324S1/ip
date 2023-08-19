import extensions.tasks.Deadline;
import extensions.tasks.Event;
import extensions.tasks.Task;
import extensions.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // read user input
    private static final Scanner sc = new Scanner(System.in);
    // store list of tasks
    private static final ArrayList<Task> list = new ArrayList<>();
    // check if chat has ended
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Prints the number of tasks in the list.
     */
    private static void printNumberOfTasks() {
        String taskOrTasks = list.size() == 1 ? "task" : "tasks";
        System.out.printf("Now you have %d %s in the list.%n", list.size(), taskOrTasks);
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     */
    private static void markTaskAsDone(int num) {
        int index = num - 1;
        list.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     */
    private static void unmarkTaskAsDone(int num) {
        int index = num - 1;
        list.get(index).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     */
    private static void addToDoTask(String description) {
        Task task = new ToDo(description);
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by The date/time of the Deadline task.
     */
    private static void addDeadlineTask(String description, String by) {
        Task task = new Deadline(description, by);
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event task.
     * @param end The end date/time of the Event task.
     */
    private static void addEventTask(String description, String start, String end) {
        Task task = new Event(description, start, end);
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Parses the input.
     *
     * @param input The input from the user.
     * @return A String array where the 1st element is the command and the 2nd element is the argument.
     */
    private static String[] parseCommand(String input) {
        // check if input is a command with arguments (e.g. mark 2)
        String[] splitInputs = input.split(" ");

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

    private static void executeCommand(String[] command) {
        switch (command[0]) {
        case "bye":
            endChat();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            markTaskAsDone(Integer.parseInt(command[1]));
            break;
        case "unmark":
            unmarkTaskAsDone(Integer.parseInt(command[1]));
            break;
        case "todo":
            addToDoTask(command[1]);
            break;
        case "deadline":
            String[] deadlineArgs = command[1].split(" /by ");
            addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            String[] eventArgs = command[1].split(" /from ");
            String[] eventTimes = eventArgs[1].split(" /to ");
            addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
            break;
        default:

        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");

        while (!hasEndedChat) {
            String input = sc.nextLine();
            String[] command = parseCommand(input);
            executeCommand(command);
        }
    }
}
