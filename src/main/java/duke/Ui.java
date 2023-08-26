package duke;

import task.Task;
import java.util.ArrayList;

public class Ui {
    private final String CHATBOTNAME = "notDuke";
    private final String INTRO = "Hello! I'm " + this.CHATBOTNAME + "\n"
            + "What can I do for you?";
    private final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    public static String line = "-------------------------------------------------------------------------------------";

    /**
     * UI constructor.
     * Prints the introduction message as well
     */
    public Ui() {
        System.out.printf("%s\n%s\n%s\n", Ui.line, INTRO, Ui.line);
    }

    /**
     * Prints the error message in an exception.
     * @param e
     */
    public void showExceptionError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print that the file path is empty.
     */
    public void emptyFilePath() {
        System.out.println("File path is empty!");
    }

    /**
     * Prints out a horizontal line, typically for separating messages.
     */
    public void createLine() {
        System.out.println(Ui.line);
    }

    /**
     * Prints out the exit message when user exits the program.
     */
    public void exit() {
        System.out.printf("%s\n%s\n", EXITMESSAGE, Ui.line);
    }

    /**
     * Prints the String representation of tasks in the given tasklist with numbering for choice.
     *
     * @param taskList Tasklist to be listed from
     */
    public void listTask(TaskList taskList) {
        ArrayList<Task> arrList = taskList.getArrList();
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : arrList) {
            counter ++;
            System.out.printf("%d.%s\n", counter, task.toString());
        }
    }

    /**
     * Prints the display when user marks a task.
     *
     * @param tasks Tasklist containing the task to be marked.
     * @param choice int representing the user's choice of task to mark
     */
    public void displayMarkTask(TaskList tasks, int choice) {
        System.out.printf("Nice! I've marked this task as done:\n" +
                "  %s\n", tasks.taskToString(choice));
    }

    /**
     * Prints the display when user unmarks a task.
     *
     * @param tasks Tasklist containing the task to be unmarked
     * @param choice int representing the user's choice of task to mark
     */
    public void displayUnmarkTask(TaskList tasks, int choice) {
        System.out.printf("OK, I've marked this task as not done yet:\n" +
                "  %s\n", tasks.taskToString(choice));
    }

    /**
     * Prints the display when a user deletes a task.
     *
     * @param removedTask Task that is removed
     * @param tasks The list of tasks that the task was removed from
     */
    public void displayDeleteTask(Task removedTask, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n" +
                        "  %s\n" +
                        "Now you have %d tasks in the list.\n"
                , removedTask.toString(), tasks.size());
    }

    /**
     * Prints the display when a user adds a task.
     *
     * @param addedTask Task that is added
     * @param tasks The list of tasks that the task was removed from
     */
    public void displayAddTask(Task addedTask, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", addedTask.toString(), tasks.size());
    }
}
