package Duke.UI;

import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.Commands;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

import java.util.Scanner;

/**
 * Controls any user interaction with the user.
 * Examples: text output, error messages
 */
public class Ui {

    private TaskList taskList;
    private Storage storage;

    private static final String SEPARATOR_LINE = "____________________________________________________________";

    /**
     * Constructor for an Ui handler.
     *
     * @param taskList
     */
    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }


    /**
     * Begins interaction with the user
     */
    public void beginLogging() {

        printDivider();
        String entranceMsg = "Hello! I'm Elon Musk.\n" +
                "What can I do for you?";
        System.out.println(entranceMsg);
        printDivider();

        String inputString = "";

        Scanner keyboard = new Scanner(System.in);

        loop:
        while (true) {
            try {
                inputString = keyboard.nextLine();
                printDivider();
                boolean canContinue = Parser.parse(inputString, taskList, storage);
                if (!canContinue) {
                    break;
                }
                printDivider();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    /**
     * Prints feedback to the user on what and how a Task got modified,
     * based on the user's command.
     *
     * @param command
     */
    public static void printResult(Commands command, Task task, TaskList listContainer) {
        switch (command) {
            case TODO:
            case DEADLINE:
            case EVENT: {
                System.out.println("\uD83D\uDE0A I've added a new task: " + task.toString());
                System.out.println("Now you have " + listContainer.getSize() + " tasks!");
                break;
            }
            case MARK: {
                System.out.println("Nice! I've marked this task as done: \n    " + task.toString());
                break;
            }
            case UNMARK: {
                System.out.println("Nice! I've marked this task as undone: \n    " + task.toString());
                break;
            }
            case DELETE: {
                System.out.println("\uD83D\uDE0A I've removed this task: " + task.toString());
                break;
            }
            case LIST: {
                System.out.println(listContainer);
                break;
            }
            case BYE: {
                String exitMsg = "Bye! Hope to see you again soon.";
                System.out.println(exitMsg);

                break;
            }
        }
    }

    /**
     * Prints a simple divider line to the screen.
     */
    public static void printDivider() {
        System.out.println(SEPARATOR_LINE);
    }

}
