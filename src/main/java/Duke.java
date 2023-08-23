import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /** An ArrayList to hold tasks entered by the User. */
    private static ArrayList<String> tasks;

    /**
     * Draws a line separating each conversation.
     *
     */
    public static void drawLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Adds the given task to the tasks ArrayList.
     * Prints that the task has been added.
     *
     * @param task The task to add.
     *
     */
    public static void addTask(String task) {
        tasks.add(task);
        System.out.println("\tadded: " + task);
        drawLine();
    }

    /**
     * Lists the tasks present in tasks ArrayList.
     */
    public static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t");
            System.out.print(i + 1);
            System.out.print(". " + tasks.get(i) + "\n");
        }
        drawLine();
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public static void handleUserInput() {

        Scanner scanner = new Scanner(System.in);
        tasks = new ArrayList<String>();
        System.out.println();
        String userInput;
        userInput = scanner.nextLine();
        drawLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }

            System.out.println();
            userInput = scanner.nextLine();
            drawLine();
        }
    }

    public static void main(String[] args) {
        drawLine();
        System.out.println("\tHello I am Vishnu.");
        System.out.println("\tWhat can I do for you?");
        drawLine();
        handleUserInput();
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine();
    }
}
