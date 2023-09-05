package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Provides methods related to user interface.
 */
public class Ui {
    /**
     * Displays the EchoBot logo.
     */
    public static void showLogo() {
        System.out.println("     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n");
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void showHorizontalLine() {
        System.out.println("   ____________________________________________________________________________________");
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        showHorizontalLine();
        System.out.println("    Hello! I'm EchoBot");
        showLogo();
        System.out.println("    What can I do for you?\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public static void showTasks(ArrayList<Task> tasks) {
        showHorizontalLine();
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("    " + (i + 1) + "." + task.toString());
        }

        showHorizontalLine();
    }

    /**
     * Displays the goodbye message.
     */
    public static void showByeMessage() {
        showHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showHorizontalLine();
    }
}
