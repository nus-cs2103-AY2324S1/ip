package duke.utils;

import duke.task.Task;

import java.util.List;
/**
 * The `Ui` class handles user interface interactions and provides methods for printing messages
 * and information to the console.
 */
public class Ui {

    /**
     * Prints a greeting message with the application's logo.
     */
    public void printGreeting() {
        System.out.println("\n" +
                " ____   __   ____   __  \n" +
                "/ ___) / _\\ (  _ \\ / _\\ \n" +
                "\\___ \\/    \\ )   //    \\\n" +
                "(____/\\_/\\_/(__\\_)\\_/\\_/\n");
        System.out.println("Hello! I'm Sara");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a farewell message when the user exits the application.
     */
    public void printFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a list of tasks from the provided `toDoList`.
     * @param toDoList The list of tasks to be displayed.
     */
    public void printList(List<Task> toDoList) {
        System.out.println("Here are the tasks in your List:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(((i + 1) + ". " + toDoList.get(i)));
        }
    }
}
