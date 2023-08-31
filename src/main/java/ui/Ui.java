package ui;

import task.Task;
import task.TaskList;

public class Ui {

    public Ui() {

    }

    public static final String WELCOME_MESSAGE = "-----------------------------------------\n"
            + "Hello! I'm Emiya\n"
            + "What can I do for you?\n";

    public static final String EXIT_MESSAGE = "-----------------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-----------------------------------------\n";

    public static final String UBW = "-----------------------------------------\n"
            + "Unknown to death nor known to life" + "\n"
            + "-----------------------------------------\n";

    public static final String DEAD = "-----------------------------------------\n"
            + "People die if they are killed!" + "\n"
            + "-----------------------------------------\n";

    public static String addedSingularMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " task in your list!\n"
                + "-----------------------------------------\n";
    }

    public static String addedPluralMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list!\n"
                + "-----------------------------------------\n";
    }
}
