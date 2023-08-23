import java.util.Scanner;

/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {
    // Due to how the taskCount variable is used as the id and
    // array access position, index 0 will always be unused.
    // taskList is effectively 1-indexed
    private static final Task[] taskList = new Task[100];

    /**
     * Main process.
     * Greets the user and waits for user input.
     * Then, responds appropriately.
     *
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Greetings, Master. Jeeves at your service");
        System.out.println("How may I serve you today?\n");

        // Waits for user input and process it accordingly
        while (true) {
            Scanner sc = new Scanner(System.in);
            // Reads the user input
            String currentCommand = sc.nextLine();
            // Performs a different action depending on the input received
            // By default the program creates and adds a new Task to the taskList unless
            // a specific pre-defined command is received.
            switch (currentCommand) {
            case "list":
                // Displays the current list of tasks tracked and their status
                for (int i = 1; i <= Task.getTaskCount(); i++) {
                    Jeeves.displayTask(i);
                }
                // Prints an empty line for aesthetic reasons
                System.out.print("\n");
                break;
            case "bye":
                // Displays the farewell message and terminates the application
                System.out.println("I bid you farewell, Master");
                System.exit(0);
            default :
                // By default, create a new Task with the provided description and add it to the taskList.
                Task newTask = new Task(currentCommand);
                taskList[Task.getTaskCount()] = newTask;
                System.out.println("Task added: " + currentCommand + "\n");
                break;
            }
        }
    }

    private static void displayTask(int id) {
        // Checks if a task has its status marked as complete
        // and displays the appropriate visual
        if (taskList[id].getStatus()) {
            System.out.println(id + "."
                    + "[X] "
                    + taskList[id].getDesc());
        } else {
            System.out.println(id + "."
                    + "[ ] "
                    + taskList[id].getDesc());
        }
    }
}
