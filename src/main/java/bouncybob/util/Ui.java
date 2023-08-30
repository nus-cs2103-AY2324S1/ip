package bouncybob.util;

import bouncybob.BouncyBob;
import bouncybob.task.Task;

/**
 * Handles the user interface of the BouncyBob application.
 */
public class Ui {
    private static final String TOP_BORDER = "================================================";
    private static final String MIDDLE_BORDER = "|                                              |";
    private static final String BOTTOM_BORDER = "================================================";

    /**
     * Prints the goodbye message.
     */
    public static void printBye() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|        BouncyBob: Bye! Bounce back soon!     |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public static void printDatabase(TaskList taskList) {
        System.out.println(TOP_BORDER);
        if (taskList.isEmpty()) {
            System.out.println("Balls! You currently do not have any tasks!");
        } else {
            System.out.println("Here are your tasks!");
            for (Task curTask : taskList.getTasks()) {
                printTaskStatus(curTask);
            }
        }
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the status of a specific task after an action.
     *
     * @param task The task whose status is to be displayed.
     * @param action The action performed on the task.
     */
    public static void printTaskStatus(Task task, BouncyBob.Action action) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println(TOP_BORDER);
        switch(action) {
            case MARK:
                System.out.println("You've done it, very bouncy!");
                break;
            case UNMARK:
                System.out.println("Gotta pump for air! It's unmarked!");
                break;
            case DELETE:
                System.out.println("Task deleted: ");
                break;
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the status of a specific task.
     *
     * @param task The task whose status is to be displayed.
     */
    public static void printTaskStatus(Task task) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
    }

    /**
     * Prints the number of tasks in the list after a new task is added.
     *
     * @param pointer The index of the last task in the list.
     * @param newTask The new task that was added.
     */
    public static void printTaskCount(int pointer, Task newTask) {
        System.out.println(TOP_BORDER);
        System.out.println("Added to database: " + newTask.getDescription());
        String s = pointer == 0 ? "task" : "tasks";
        String str = String.format("Currently, you have %s %s, start bouncing!", pointer + 1, s);
        System.out.println(str);
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the introductory message when the application starts.
     */
    public static void printIntro() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|                   Hey there!                 |");
        System.out.println("|      I'm BouncyBob, your bubbly chatbot!     |");
        System.out.println("| Wow you look very round today, like a ball!  |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the message for an IllegalArgumentException.
     *
     * @param e The IllegalArgumentException that was thrown.
     */
    public static void printIllegalArgumentException(IllegalArgumentException e) {
        System.out.println(TOP_BORDER);
        System.out.println("Oops! " + e.getMessage());
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the message for a DateTimeParseException.
     */
    public static void printDateTimeParseException() {
        System.out.println(TOP_BORDER);
        System.out.println("Date must be in yyyy-mm-dd hhmm format!");
        System.out.println(BOTTOM_BORDER);
    }

    /**
     * Prints the message when an index is out of bounds.
     */
    public static void printIndexOutOfBound() {
        System.out.println(TOP_BORDER);
        System.out.println("Make sure your index is within the length of the list!");
        System.out.println(BOTTOM_BORDER);
    }
}
