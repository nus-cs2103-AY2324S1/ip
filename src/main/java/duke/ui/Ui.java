package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static void showLogo() {
        System.out.println("     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n");
    }

    public static void showHorizontalLine() {
        System.out.println("   ____________________________________________________________________________________");
    }
    public void showWelcomeMessage() {
        showHorizontalLine();
        System.out.println("    Hello! I'm EchoBot");
        showLogo();
        System.out.println("    What can I do for you?\n");
    }

    public static void showTasks(ArrayList<Task> tasks) {
        showHorizontalLine();
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("    " + (i + 1) + "." + task.toString());
        }

        showHorizontalLine();
    }

    public static void showByeMessage() {
        showHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showHorizontalLine();
    }
}
