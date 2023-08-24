import java.util.Scanner;

public class ChatBuddy {
    // store a list of tasks
    static Task[] list = new Task[100];
    static int numOfTasks = 0;

    /**
     * Add task to list array.
     *
     * @param task The task to add into the list of tasks.
     */
    private static void addTask(Task task) {
        // add task to list
        list[numOfTasks] = task;
        numOfTasks++;

        // display message
        printHorizontalLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
        printHorizontalLine();
    }

    /**
     * Add a todo task into the task list.
     *
     * @param userInput The input provided by the user that starts with "todo".
     */
    private static void addToDo(String userInput) {
        String taskDescription = userInput.substring(5);
        ToDo todo = new ToDo(taskDescription);
        addTask(todo);
    }

    /**
     * Add a deadline task into the task list.
     *
     * @param userInput The input provided by the user that starts with "deadline".
     *                  The userInput should include /by.
     */
    private static void addDeadline(String userInput) {
        int byCommandIndex = userInput.indexOf("/by");
        String taskDescription = userInput.substring(9, byCommandIndex - 1);
        String by = userInput.substring(byCommandIndex + 4);
        Deadline deadline = new Deadline(taskDescription, by);
        addTask(deadline);
    }

    /**
     * Add an event task into the task list.
     *
     * @param userInput The input provided by the user that starts with "event".
     *                  The userInput should include /from and /to.
     */
    private static void addEvent(String userInput) {
        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");
        String taskDescription = userInput.substring(6, fromIndex - 1);
        String from = userInput.substring(fromIndex + 6, toIndex - 1);
        String to = userInput.substring(toIndex + 4);
        Event event = new Event(taskDescription, from, to);
        addTask(event);
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
            System.out.println(String.format("    %1s.%2s", i + 1, task.toString()));
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
            } else if (userInput.startsWith("todo")) {
                addToDo(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput);
            }
            userInput = scanner.nextLine();
        }

        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
