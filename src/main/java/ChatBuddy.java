import java.util.Scanner;
public class ChatBuddy {
    // store a list of tasks
    static Task[] list = new Task[100];
    static int numOfTasks = 0;

    /**
     * Add task to list array.
     *
     * @param task The task string to add to the list of tasks
     */
    private static void addTask(String taskDescription) {
        // add task to list
        Task task = new Task(taskDescription);
        list[numOfTasks] = task;
        numOfTasks++;

        // display message
        printHorizontalLine();
        System.out.println("    added: " + taskDescription);
        printHorizontalLine();
    }

    /** Displays the list of tasks. */
    private static void printTaskList() {
        printHorizontalLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.length; i++) {
            Task task = list[i];
            if (task == null) {
                // there are no more tasks
                break;
            }
            System.out.println(String.format("    %1s.%2s", i + 1, task.getStatusIconAndDescription()));
        }
        printHorizontalLine();
    }

    /** Displays a horizontal line. */
    private static void printHorizontalLine() {
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHorizontalLine();
        System.out.println("    Hello! I'm Chat Buddy!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList();
            } else if (userInput.matches("mark [1-9][0-9]*")) {
                String indexString = userInput.substring(5);
                int taskIndex = Integer.parseInt(indexString) - 1;
                Task task = list[taskIndex];
                task.markAsDone();
            } else if (userInput.matches("unmark [1-9][0-9]*")) {
                String indexString = userInput.substring(7);
                int taskIndex = Integer.parseInt(indexString) - 1;
                Task task = list[taskIndex];
                task.markAsNotDone();
            } else {
                addTask(userInput);
            }
            userInput = scanner.nextLine();
        }

        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
