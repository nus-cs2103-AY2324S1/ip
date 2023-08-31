package roo;

import roo.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    private final TaskList tasks;

    /**
     * Constructs a Ui object with the given TaskList.
     * @param tasks The TaskList associated with this Ui instance.
     */
    public Ui (TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        System.out.println("Hello from\n" + logo + hello);
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     * @return The user's input as a String.
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner and displays an exit message.
     */
    public void close() {
        scanner.close();
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    /**
     * Displays the list of tasks.
     */
    public void list() {
        System.out.println("Although I dunwan to list... But here is your list:");
        tasks.list();
    }

    /**
     * Clears all tasks and displays a message indicating that.
     */
    public void clear() {
        tasks.clear();
        System.out.println("All tasks cleared\n");
    }

    /**
     * Lists tasks matching the given date.
     * @param date The date for which tasks are to be listed.
     */
    public void listDateEvents(LocalDate date) {
        System.out.println("Your task on " + date.toString() + "...");
        tasks.listDateEvents(date);
    }

    /**
     * Lists tasks matching a given keyword.
     * @param keyword The keyword to search for in tasks.
     */
    public void find(String keyword) {
        System.out.println("Nah, your matching tasks:");
        tasks.find(keyword);
    }

    /**
     * Marks a task as done and displays a completion message on the task to mark as done.
     * @param index The index of the task to mark as done.
     */
    public void markDone(int index) {
        tasks.markDone(index);
        System.out.println("Yay! \"" + tasks.taskDetails(index) + "\" done liao!!\n");
    }

    /**
     * Marks a task as undone and displays a message on the task to mark as undone.
     * @param index The index of the task to mark as undone.
     */
    public void markUndone(int index) {
        tasks.markUndone(index);
        System.out.println("Hmm... Why just now don't mark \"" + tasks.taskDetails(index) + "\" as done properly...\n");
    }

    /**
     * Deletes a task, displays a removal message about the task to delete, and shows the updated task count.
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        System.out.println("Okay!! Task \"" + tasks.taskDetails(index) + "\" removed :) ");
        tasks.delete(index);
        System.out.println("You still have " + tasks.size() + " tasks in the list\n");
    }

    /**
     * Adds a task, displays an addition message with the added task details, and shows the updated task count.
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.addNew(task);
        System.out.println("\"" + task.toString() + "\" added :)");
        System.out.println("Now got " + tasks.size() + " task liao T_T\n");
    }
}
