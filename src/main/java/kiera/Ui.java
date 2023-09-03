package kiera;

import java.time.LocalDate;
import java.util.Scanner;

import kiera.task.Task;
import kiera.tasktype.TaskType;

/**
 * Displays messages and reads user input.
 */
public class Ui {
    private final String Line = "   ---------------------------------------------";

    public Ui() {
    }

    /**
     * Shows an error message indicating that tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("tasks cannot be loaded; starting with an empty list!");
    }

    /**
     * Shows an error message with the specified content.
     *
     * @param e Error message to be displayed.
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(Line);
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showHello() {
        System.out.println(Line
                + "\n"
                + "    "
                + "hi, it's kiera.\n"
                + "    "
                + "what do you need?\n"
                + Line);
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showBye() {
        System.out.println(Line
                + "\n"
                + "    "
                + "muaks! <3\n"
                + Line);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        if (in.hasNext()) {
            return in.nextLine();
        }
        return "sorry, i didn't quite catch that.";
    }

    /**
     * Shows a notice indicating that a task has been added.
     *
     * @param task Task that has been added.
     * @param t Type of task.
     * @param listSize Current size of the task list.
     */
    public void showAddNotice(Task task, TaskType t, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        System.out.println("    "
                + "alright, one "
                + t
                + " has been added: \n"
                + "      "
                + task
                + "\n    "
                + listSize
                + " more "
                + plural
                + " to go!");
    }

    /**
     * Shows a notice indicating that a task has been deleted.
     *
     * @param task Task to be deleted.
     * @param listSize Current size of the task list.
     */
    public void showDeleteNotice(Task task, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        System.out.println("    "
                + "alright, this task is gone: \n"
                + "      "
                + task
                + "\n    "
                + listSize
                + " more "
                + plural
                + " left!");
    }

    /**
     * Shows a notice indicating filtered tasks based on date and task type.
     *
     * @param d Date to be filtered by.
     * @param t Type of tasks.
     * @param content Content of the filtered tasks.
     * @param listSize Current size of the filtered task list.
     */
    public void showFilteredNotice(LocalDate d, TaskType t, String content, int listSize) {
        String plural = listSize == 1 ? " " : "s ";
        String verb = listSize == 1 ? "is " : "are ";
        System.out.println("    "
                + "there "
                + verb
                + listSize
                + t
                + plural
                + "due on "
                + d
                + ":\n     "
                + content
                + "    "
        );
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param content Content of the task list to be displayed.
     */
    public void showList(String content) {
        System.out.println("    you need to get these done:\n" + content.stripTrailing());
    }
}
