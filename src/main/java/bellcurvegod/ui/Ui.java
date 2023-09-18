package bellcurvegod.ui;

import java.util.ArrayList;

import bellcurvegod.task.Task;

/**
 * Encapsulates the UI.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = "\n"
        + "  ____       _ _    _____                        _____           _ \n"
        + " |  _ \\     | | |  / ____|                      / ____|         | |\n"
        + " | |_) | ___| | | | |    _   _ _ ____   _____  | |  __  ___   __| |\n"
        + " |  _ < / _ \\ | | | |   | | | | '__\\ \\ / / _ \\ | | |_ |/ _ \\ / _` |\n"
        + " | |_) |  __/ | | | |___| |_| | |   \\ V /  __/ | |__| | (_) | (_| |\n"
        + " |____/ \\___|_|_|  \\_____\\__,_|_|    \\_/ \\___|  \\_____|\\___/ \\__,_|";

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println(LOGO);
        showLine();
        System.out.println("Hello! I'm Bell Curve God.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public static String getLine() {
        return HORIZONTAL_LINE;
    }

    /**
     * Prints a horizontal line.
     */
    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the ExitMessage.
     */
    public static void showExitMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints the FindMessage.
     */
    public static void showFindMessage(ArrayList<Task> matchingTasks) {
        showLine();
        if (!matchingTasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        } else {
            System.out.println("There is no task in your list that matches this keyword.");
        }
        showLine();
    }

    /**
     * Prints the ListMessage.
     */
    public static void showListMessage(ArrayList<Task> tasks) {
        showLine();
        if (!tasks.isEmpty()) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("There is no task in your list.");
        }
        showLine();
    }

    /**
     * Prints the MarkMessage.
     */
    public static void showMarkMessage(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Prints the UnmarkMessage.
     */
    public static void showUnmarkMessage(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    /**
     * Prints the AddTaskMessage.
     */
    public static void showAddTaskMessage(Task newTask, int numOfTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Prints the DeleteTaskMessage.
     */
    public static void showDeleteTaskMessage(Task task, int numOfTasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Prints the WrongDateFormatMessage.
     */
    public static void showWrongDateFormatMessage() {
        showLine();
        System.out.println("Please enter your time in the following format:");
        System.out.println("yyyy-mm-dd");
        System.out.println("E.g. 2019-10-15");
        showLine();
    }
}
