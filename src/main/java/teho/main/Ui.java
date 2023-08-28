package teho.main;

import teho.main.Task;
import teho.main.TaskList;
/**
 * Deals with interactions with the user through generating messages.
 */

public class Ui {
    /**
     * Generates hello message when TehO chatbot starts.
     */
    public static void generateHelloMessage() {
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");
    }

    /**
     * Generates goodbye message when user types bye and TehO chatbot exits.
     */
    public static void generateGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Generates message containing list of tasks.
     *
     * @param taskList List of tasks to be generated.
     */
    public static void generateList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    /**
     * Generates message to show that task is marked as done.
     *
     * @param task Task marked as done.
     */
    public static void generateMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Generates message to show that task is marked as undone.
     *
     * @param task Task marked as undone.
     */
    public void generateUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Generates message to show that ToDo task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     */
    public void generateAddToDoMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    /**
     * Generates message to show that Deadline task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     */
    public void generateAddDeadlineMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    /**
     * Generates message to show that Event task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     */
    public void generateAddEventMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    /**
     * Generates message to show that task is deleted from list.
     *
     * @param task Task deleted from list.
     * @param taskList List of tasks to delete task from.
     */
    public void generateDeleteMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void showLoadingError() {
        System.out.println("OHNO! Loading error!");
    }
}
