package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents the messages printed to the user.
 */
public class Ui {
    /**
     * Prints the task list to the user.
     *
     * @param taskList The list of tasks.
     * @return task list printed to the user.
     */
    public String printList(TaskList taskList) {
        String taskListMessage = "Here are the tasks in your list:\n";
        taskListMessage += taskList.printTaskList();
        return taskListMessage;
    }

    /**
     * Prints the hello message to the user.
     *
     * @return String of hello printed to the user.
     */
    public String showHelloMessage() {
        String helloMessage = "Hello! I'm Thinh's chatbot\n    What can I do for you?";
        return helloMessage;
    }

    /**
     * Prints the goodbye message to the user.
     *
     * @return String of bye printed to the user.
     */
    public String showByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";
        return byeMessage;
    }

    /**
     * Prints the add task message to the user.
     *
     * @param task The task added to the task list.
     * @param newSize The new size of the task list.
     * @return String represents the confirmation of adding task printed to the user.
     */
    public String addTask(Task task, int newSize) {
        String addTaskMessage = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + newSize
                + " tasks in the list.";
        return addTaskMessage;
    }

    /**
     * Prints the delete task message to the user.
     *
     * @param task The task deleted from the task list.
     * @param newSize The new size fo the task list.
     * @return String represents the confirmation of deleting task printed to the user.
     */
    public String deleteTask(Task task, int newSize) {
        String deleteTaskMessage = "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + newSize
                + " tasks in the list.";
        return deleteTaskMessage;
    }

    /**
     * Prints the message that shows the task has marked done.
     *
     * @param task The task to be marked done.
     * @return String represents the confirmation of marking task done printed to the user.
     */
    public String markTaskDone(Task task) {
        String markTaskDoneMessage = "Nice! I've marked this task as done:\n" + task.toString();
        return markTaskDoneMessage;
    }

    /**
     * Prints the message that shows the task has marked not done.
     *
     * @param task The task to be marked not done.
     * @return String represents the confirmation of marking task not done printed to the user.
     */
    public String markTaskNotDone(Task task) {
        String markTaskNotDoneMessage = "OK, I've marked this task as not done yet:\n" + task.toString();
        return markTaskNotDoneMessage;
    }

    /**
     * Prints the error occurred when running in the program.
     *
     * @param e The exception thrown in the program.
     * @return String represents the error printed to the user.
     */
    public String printError(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints the matching task to user.
     *
     * @param tasks Array contains matching tasks.
     * @return The matching tasks printed to the user.
     */
    public String printMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder matchingTaskMessage = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 0;
        for (Task task: tasks) {
            matchingTaskMessage.append((++index)).append(".").append(task.toString());
        }
        return matchingTaskMessage.toString();
    }
}
