package juke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    static void printLine() {
        System.out.println("_______________________________________________________");
    }
    public String readInput() {
        return scanner.nextLine();
    }
    public void printStart() {
        //Introduce itself to the user
        System.out.println("Hello! I'm Juke!");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printError(JukeError error) {
        System.out.println(error.getMessage());
        printLine();
    }

    public void printBye() {
        //Say goodbye
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        scanner.close();  // Close the scanner before exiting
    }

    public void printList(ArrayList<Task> tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ": " + task.toString());
            count++;
        }
        printLine();
    }

    public void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString());
    }

    public void mark(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task.toString());
    }

    public void delete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

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
