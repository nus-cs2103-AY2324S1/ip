package emiya.ui;

import emiya.task.Task;
import emiya.task.TaskList;

public class Ui {

    public Ui() {

    }

    public static final String WELCOME_MESSAGE = "-----------------------------------------\n"
            + "Hello! I'm Emiya\n"
            + "What can I do for you?\n"
            + "-----------------------------------------\n";

    public static final String EXIT_MESSAGE = "-----------------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-----------------------------------------\n";

    public static final String UBW = "-----------------------------------------\n"
            + "Unknown to death nor known to life" + "\n"
            + "-----------------------------------------\n";

    public static final String DEAD = "-----------------------------------------\n"
            + "People die if they are killed!" + "\n"
            + "-----------------------------------------\n";

    public String addedSingularMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " emiya.task in your list!\n"
                + "-----------------------------------------\n";
    }

    public String addedPluralMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure! I have added this task to the list:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list!\n"
                + "-----------------------------------------\n";
    }

    public static String deletedSingularMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure, I shall now delete the following task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " task in your list!\n"
                + "-----------------------------------------\n";
    }

    public String deletedPluralMessage(Task task, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Sure, I shall now delete the following task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list!\n"
                + "-----------------------------------------\n";
    }

    public String markedMessage(int position, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Nice job! I have marked this task as done:\n" + taskList.get(position-1) + "\n"
                + "-----------------------------------------\n";
    }

    public String unmarkedMessage(int position, TaskList taskList) {
        return "-----------------------------------------\n" +
                "Oof, alright I have set this task as unmarked:\n" + taskList.get(position-1) + "\n"
                + "-----------------------------------------\n";
    }
}
