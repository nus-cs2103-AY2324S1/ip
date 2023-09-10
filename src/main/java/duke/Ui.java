package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates the response and User Interface of the application.
 *
 * @author Donovan Chan Jia Jun
 */
public class Ui {
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * UI constructor.
     */
    public Ui() {

    }

    /**
     * Prints the error message in an exception.
     *
     * @param e Exception which message should be printed out
     */
    public String showExceptionError(Exception e) {
        return String.format(e.getMessage());
    }

    /**
     * Print that the file path is empty.
     * */
    public void emptyFilePath() {
        System.out.println("File path is empty!");
    }

    /**
     * Prints out the exit message when user exits the program.
     */
    public String exit() {
        return this.EXIT_MESSAGE;
    }

    /**
     * Prints the String representation of tasks in the given tasklist with numbering for choice.
     *
     * @param taskList Tasklist to be listed from
     */
    public String listTask(TaskList taskList) {
        ArrayList<Task> arrList = taskList.getArrList();
        String message = "";
        int counter = 0;
        message = "Here are the tasks in your list:\n";
        for (Task task : arrList) {
            counter++;
            message += String.format("%d.%s\n", counter, task.toString());
        }
        return message;
    }

    /**
     * Prints the display when user marks a task.
     *
     * @param tasks Tasklist containing the task to be marked.
     * @param choice int representing the user's choice of task to mark
     */
    public String displayMarkTask(TaskList tasks, int choice) {
        return "Nice! I've marked this task as done:\n"
                + tasks.taskToString(choice);
    }

    /**
     * Prints the display when user unmarks a task.
     *
     * @param tasks Tasklist containing the task to be unmarked
     * @param choice int representing the user's choice of task to mark
     */
    public String displayUnmarkTask(TaskList tasks, int choice) {
        return "OK, I've marked this task as not done yet:\n"
                + tasks.taskToString(choice);
    }

    /**
     * Prints the display when a user deletes a task.
     *
     * @param removedTask Task that is removed
     * @param tasks The list of tasks that the task was removed from
     */
    public String displayDeleteTask(Task removedTask, TaskList tasks) {
        return String.format("Noted. I've removed this task:\n"
                        + "  %s\n"
                        + "Now you have %d tasks in the list.\n",
                removedTask.toString(), tasks.size());
    }

    /**
     * Prints the display when a user adds a task.
     *
     * @param addedTask Task that is added
     * @param tasks The list of tasks that the task was removed from
     */
    public String displayAddTask(Task addedTask, TaskList tasks) {
        return String.format("Got it. I've added this task:\n"
                + "  %s\n"
                + "Now you have %d tasks in the list.\n", addedTask.toString(), tasks.size());
    }

    /**
     * Prints the Task that is found using the Find function.
     *
     * @param arrList List of tasks to display
     */
    public String displayMatchingTask(ArrayList<Task> arrList) {
        int size = arrList.size();
        String msg = "";
        if (size > 0) {
            msg += "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= size; i++) {
                msg += String.format("%d.%s\n", i, arrList.get(i - 1).toString());
            }
        }
        return msg;
    }

    /**
     * Returns a string to tell user that the duplicate task is rejected.
     *
     * @return String response message to duplicate task
     */
    public String displayDuplicateTask() {
        String responseMessage = "This is a duplicate task!\nTask was not added";
        return responseMessage;
    }
}
