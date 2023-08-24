import java.util.Scanner;
public class ChatBuddy {
    // store a list of tasks
    static String[] list = new String[100];
    static int numOfTasks = 0;

    /**
     * Add task to list array.
     *
     * @param task The task string to add to the list of tasks
     */
    private static void addTask(String task) {
        // add task to list
        list[numOfTasks] = task;
        numOfTasks++;

        // display message
        printHorizontalLine();
        System.out.println("    added: " + task);
        printHorizontalLine();
    }

    /** Displays the list of tasks. */
    private static void printTaskList() {
        printHorizontalLine();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                // there are no more tasks
                break;
            }
            System.out.println(String.format("    %1s. %2s", i + 1, list[i]));
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
