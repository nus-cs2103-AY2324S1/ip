package juke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a line to separate different sections.
     */
    static void printLine() {
        System.out.println("_______________________________________________________");
    }

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the message to welcome the user.
     */
    public void printStart() {
        //Introduce itself to the user
        System.out.println("Hello! I'm Juke!");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints the error message.
     * @param error The JukeError which was encountered.
     */
    public void printError(JukeError error) {
        System.out.println(error.getMessage());
        printLine();
    }

    /**
     * Prints bye to the user.
     */
    public void printBye() {
        //Say goodbye
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        scanner.close();  // Close the scanner before exiting
    }

    /**
     * Prints the lists of tasks that the user has saved.
     * @param tasks The list of tasks to be printed.
     */
    public void printList(ArrayList<Task> tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ": " + task.toString());
            count++;
        }
        printLine();
    }

    /**
     * Prints message after a task is marked as uncompleted.
     * @param task The task which was unmarked.
     */
    public void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString());
    }

    /**
     * Prints message after a task is marked as completed.
     * @param task The task which was marked.
     */
    public void mark(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task.toString());
    }

    /**
     * Prints message after a task is deleted.
     * @param task The task which was deleted.
     * @param size The number of tasks in the TaskList.
     */
    public void delete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints message after a task is created.
     * @param task The task which was created.
     * @param size The number of tasks in the TaskList.
     */
    public void createTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints out corresponding tasks based on search term.
     * @param results The results of the search.
     */
    public void find(ArrayList<Task> results) {
        if (results.size() == 0) {
            System.out.println("No results found.");
        } else {
            int count = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : results) {
                System.out.println(count + ". " + task.toString());
                count++;
            }
        }
        printLine();
    }

}
