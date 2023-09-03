package duke.helper;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui class handles the user interaction and print statements for the user
 */

public class Ui {
    private Scanner scan;
    private final String LINES;

    /**
     * Initialises the Ui class and sets the line string
     */
    public Ui() {
        this.LINES = "______________________________";
        this.scan = new Scanner(System.in);
    }

    /**
     * Prints the error message when cannot locate the storage file
     */
    public void showLoadingError() {
        System.out.println("Error! Cannot initialise new Storage");
    }

    /**
     * Reads the command input by the user
     * @return returns the command input by the user
     */

    public String readCommand() {
        // no string processing
        String command = this.scan.nextLine();
        return command;
    }

    /**
     * Initial Greeting message by the user
     */
    public void greet() {
        this.printLines();
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        this.printLines();
    }

    /**
     * Goodbye message to the user when closing the bot
     */
    public void bye() {
        this.printLines();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the lines per command
     */

    public void printLines() {
        System.out.println(this.LINES);
    }

    /**
     * Prints out the successful output when a task is added
     * @param length length of the TaskList currently
     * @param task task that is added to taslist
     */

    public void printAddTask(int length, Task task) {
        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + length + " meow-tasks in the list.");
        this.printLines();
    }

    /**
     * Prints out the successful output when a tas is marked
     * @param taskNumber index of the task in TaskList currently
     * @param wantedTask task that has been marked
     */

    public void printMarkTask(int taskNumber, Task wantedTask) {
        System.out.println("Nice! I've meowrked this task as done: ");
        System.out.println("   " + wantedTask);
        System.out.println(this.LINES);
    }

    /**
     * Prints out the successful output when a tas is unmarked
     * @param taskNumber index of the task in TaskList currently
     * @param wantedTask task that has been unmarked
     */
    public void printUnmarkTask(int taskNumber, Task wantedTask) {
        System.out.println("Ok, get your task done soon, I'll be waiting!");
        System.out.println(" " + wantedTask);
        System.out.println(this.LINES);
    }

    /**
     * Prints out the message when a task is deleted succesfully
     * @param length number of tasks in TaskList currently
     * @param wantedTask Task that is be removed from Tasklist
     */
    public void printDeleteTask(int length, Task wantedTask) {
        System.out.println("Meow... ok, I've removed this task: ");
        System.out.println(" " + wantedTask);
        System.out.println("Now you have " + length + " meow-tasks in the list.");
        System.out.println(this.LINES);
    }

    /**
     * Prints out the tasks stored in the TaskList
     * @param tasks TaskList to be printed
     */
    public void displayTasks(TaskList tasks) {
        System.out.println(this.LINES);
        System.out.println("Meoowww here are your tasks");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + ". " + tasks.getTask(i - 1));
        }
        System.out.println(this.LINES);
    }

    /**
     * Pirnt statement when no tasks with a given keyword is found
     * @param keyword keyword to find related tasks
     */

    public void printEmptyFind(String keyword) {
        System.out.println("Meow :( found no tasks with " + keyword);
    }

    /**
     * Print statement when successful tasks have been found
     * @param res result of the string to be printed
     */

    public void printFindRes(String res) {
        System.out.println(this.LINES);
        System.out.println("Meow Here are your matching tasks !" + res);
        System.out.println(this.LINES);
    }


}
