package duke.util;

import duke.CheeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui(){
        input = new Scanner(System.in);
    }

    /**
     * Print the error message.
     * @param error The error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Print a line.
     */
    public void showLine() {
        System.out.println("________________________________________________________");
    }

    /**
     * Print the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.CheeChat");
        System.out.println("What can I do for you?");
    }

    /**
     * read the command that the users input.
     * @return String representation of the user input.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Print out the Task that is added into the TaskList and the number of tasks
     * in the TaskList.
     * @param task The task that got added into the TaskList.
     * @param size The size of the TaskList.
     */
    public void printAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Print the task that got deleted and the remaining number of
     * tasks left in the TaskList.
     * @param task The task that is getting deleted.
     * @param size The size of the TaskList
     */
    public void printDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printFind (ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String toPrint = i + ". " + tasks.get(i).toString();
            System.out.println(toPrint);
        }
    }

    /**
     * Print the exit message.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the task that got marked as done.
     * @param task The task that got marked as done.
     */
    public void printMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Print error message when there is an invalid command.
     */
    public void printInvalidMessage() {
        try {
            throw new CheeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (CheeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print the tasks present in the TaskList.
     * @param list The string representation of all the Tasks in the TaskList.
     */
    public void printList(String list) {
        System.out.println(list);
    }
}
