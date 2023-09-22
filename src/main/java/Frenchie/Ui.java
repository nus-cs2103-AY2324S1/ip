package Frenchie;

import java.util.Scanner;

/**
 * This Ui Class deals with handling the user input returning respective success messages
 * depending on the user input.
 * This Ui class has a private Scanner attribute that takes in User input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor that creates a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the Welcome message whenever the Frenchie chatbot is started.
     */
    public void welcomeMessage() {
        String skeleton = "____________________________________________________________\n"
                + " Hello! I'm Frenchie.\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(skeleton);
    }

    /**
     * This method retrieves the UserInput before parsing it into the Parser object
     * for the Parser to make sense of the command.
     * @return user input as a String.
     */
    public String retrieveUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * This method prints out all the tasks stored in a TaskList.
     * @param tasks TaskList object that contains all the Tasks in an arraylist.
     */
    public String listTasks(TaskList tasks) {
        return tasks.listTasks();
    }

    /**
     * This method prints out the success message that a Task in a tasklist has been marked as completed.
     * @param target_task the Task to be marked as completed.
     */
    public String markTaskAsComplete(Task target_task) {
        return " Nice! I've marked this task as done: \n"
                + target_task.toString() + "\n";
    }

    /**
     * This method prints out the success message that a Task in a tasklist has been marked as incomplete.
     * @param target_task the Task to be marked as incomplete.
     */
    public String markTaskAsIncompelte(Task target_task) {
        return " OK, I've marked this task as not done yet: \n"
                + target_task.toString() + "\n";
    }

    /**
     * This method prints out the success message when a Task has been added to the TaskList.
     * @param task the Task object to be added to the TaskList.
     * @param taskList the Tasklist that the Task should be added to.
     */
    public String addTask(Task task, TaskList taskList) {
        return " Got it! I've added this task: \n"
                + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n";
    }

    /**
     * This method prints out the success message when a Task has been removed from a TaskList.
     * @param target_task the Task to be removed from the TaskList.
     * @param taskList the TaskList that the Task should be removed from.
     */
    public String deleteTask(Task target_task, TaskList taskList) {
        return "____________________________________________________________\n"
                + "Noted. I've removed this task: \n"
                + target_task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * This method prints out the exit message when a user inputs 'bye'.
     */
    public String exitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * This method closes the Scanner of the Ui object when the chatbot should be closed.
     */

    public String findMessage() {
        return "Here are the matching tasks in your list: \n";
    }

    public void closeScanner() {
        scanner.close();
    }
}
