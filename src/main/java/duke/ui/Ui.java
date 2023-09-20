package duke.ui;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import duke.exceptions.MyBotExceptions;

/**
 * The UI class provides methods for displaying messages and interacting with the user.
 * It handles printing various messages for various contexts.
 */
public class Ui {

    /**
     * Prints greeting message when the application starts.
     */
    public void showGreeting() {
        System.out.println("Hello! I'm MYBOT:)");
        System.out.println("What can I do for you?");
        System.out.println("(if you are entering a deadline/event time please enter in the format date,day,time)");
    }

    /**
     * Prints exit message when the application is exited.
     */
    public void closeGreeting() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an exception message.
     *
     * @param e The customed exception to be printed.
     */
    public void printException(MyBotExceptions e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the tasks entered in order.
     *
     * @param tasks The list of tasks to be printed.
     * @param taskCount The number of tasks in the list.
     */
    public void printTaskList(List<Task> tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints a message indicating a task has been added.
     *
     * @param task The task to be added.
     * @param taskCount The total number of tasks after new task is added.
     */
    public void printAddTask(Task task, int taskCount) {
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a message indicating a task has been completed.
     *
     * @param task The task that has been marked as completed.
     */
    public void printMarkTask(Task task) {
        System.out.println("Good job completing! I've marked these task as done:):");
        System.out.println(task.toString());
    }

    /**
     * Prints a message indicating a task has been marked as not completed.
     *
     * @param task The task that has been marked as not completed.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Prints a message indicating a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskList The updated task list after task is removed.
     */
    public void printRemoveTask(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
    }

    /**
     * Prints a message notifying users a file to store their tasks has been created.
     */
    public void printFileError() {
        System.out.println("MYBOT has created a file MYBOT.txt to record your tasks");
    }
    /**
     * Prints the list of tasks that matches user's input.
     *
     * @param matchingTasks The List of matching tasks found.
     */
    public void printMatchingTasks(List<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }

    /**
     * Prints the command that tells users there are no tasks with the keyword they put
     */
    public void printNoMatchingTasks() {
        System.out.println("Sorry! There are no matching tasks in your tasks list");
    }

    /**
     * Prints the list of tasks that has the same description as users' input
     *
     * @param matchingTasks The List of repeated tasks found.
     */
    public void printRepeatedTasks(List<Task> matchingTasks) {
        System.out.println("There are similar tasks in your task list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
        System.out.println("type 'proceed' if you still wish to add this task");
    }
}
