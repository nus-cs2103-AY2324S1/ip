import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot to keep track of your tasks.
 *
 * @author Andrew Daniel Janong
 */
public class Duke {

    /**
     * The array of tasks to keep track of the users' tasks.
     */
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * A line used as a line break.
     */
    private static final String LINE = "____________________________________________________________";

    /**
     * Sends a greeting message to the user.
     */
    private static void greetingMessage() {
        System.out.println("\t" + Duke.LINE + "\n" +
                "\t Hello I'm ADJ\n" +
                "\t What can I do for you?\n\t" +
                Duke.LINE);
    }

    /**
     * Adds a task to the task list and sends a message of the task added.
     * A task can be a ToDo, Deadline, or Event.
     * @param taskType
     * @param taskInfo
     */
    private static void addTask(String taskType, String taskInfo) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskInfo);
        } else if (taskType.equals("deadline")) {
            String[] deadlineInfo = taskInfo.split(" /by ");
            newTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        } else {
            String[] eventInfo = taskInfo.split(" /from ");
            String[] eventTime = eventInfo[1].split(" /to ");
            newTask = new Event(eventInfo[0], eventTime[0], eventTime[1]);
        }

        Duke.tasks.add(newTask);

        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n" +
                "\t Now you have " + Duke.tasks.size() + " tasks in your list. Good luck!");
    }

    /**
     * Marks a task as done.
     * @param taskIndex
     */
    private static void markTask(int taskIndex) {
        Task task = Duke.tasks.get(taskIndex - 1);
        task.markAsDone();

        System.out.println("\t Nice job! I've marked this task as done:");
        System.out.println("\t\t " + task);
    }

    /**
     * Marks a task as NOT done.
     * @param taskIndex
     */
    private static void unmarkTask(int taskIndex) {
        Task task = Duke.tasks.get(taskIndex - 1);
        task.markAsNotDone();

        System.out.println("\t What happened? I've marked this task as not done yet:");
        System.out.println("\t\t " + task);
    }

    /**
     * Lists all current tasks.
     */
    private static void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + Duke.tasks.get(i));
        }
    }

    /**
     * Echoes back the users' message.
     * @param userInput
     */
    private static void echoMessage(String userInput) {
        System.out.println("\t " + userInput);
    }

    /**
     * Sends a goodbye message to the user.
     */
    private static void goodbyeMessage() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Duke.greetingMessage();

        try {
            while (true) {
                String userInput = sc.nextLine();
                String[] inputs = userInput.split(" ", 2);
                String command = inputs[0].toLowerCase();

                System.out.println("\t" + Duke.LINE);

                if (command.equals("bye")) {
                    Duke.goodbyeMessage();
                    System.out.println("\t" + Duke.LINE);
                    break;
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    Duke.addTask(command, inputs[1]);
                } else if (command.equals("list")) {
                    Duke.listTasks();
                } else if (command.equals("mark")) {
                    Duke.markTask(Integer.parseInt(inputs[1]));
                } else if (command.equals("unmark")) {
                    Duke.unmarkTask(Integer.parseInt(inputs[1]));
                } else {
                    Duke.echoMessage(userInput);
                }

                System.out.println("\t" + Duke.LINE);
            }
        } catch (Exception e) {
            System.out.println("\t [ERROR] Sorry, an error occurred and ADJ has broken down :(");
        }

    }
}
