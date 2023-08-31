package toolpackage;

import taskpackage.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    
    private static final String TEXT_GREETING =  "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private static final String TEXT_GOODBYE =  "Bye. Hope to see you again soon!";
    private static final String TEXT_NO_DATA =  "No existing data found. New file created!";

    public Ui() {
    }

    /**
     * Prints greeting message to user.
     */
    public void showWelcome() {
        System.out.println(Ui.TEXT_GREETING);
    }

    /**
     * Prints goodbye message to user.
     */
    public void showBye() {
        System.out.println(Ui.TEXT_GOODBYE);
    }

    /**
     * Prints "No existing data" message to user.
     */
    public void showLoadingError() {
        System.out.println(Ui.TEXT_NO_DATA);
    }

    /**
     * Reads and returns the inputs by users.
     *
     * @param inputs Commands given by user.
     * @return String
     */
    public String readCommands(Scanner inputs) {
        return inputs.nextLine();
    }

    /**
     * Prints list of tasks.
     *
     * @param listOfTasks List of tasks to print.
     */
    public void printList(ArrayList<Task> listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.printf("%d.%s%n", i+1, listOfTasks.get(i).printTask());
        }
    }

    /**
     * Prints the toggling status of the task.
     *
     * @param task Task that was marked or unmarked.
     * @param keyword Word to indicate whether the task was marked as complete or incomplete.
     */
    public void toggleDone(Task task, String keyword) {
        if (keyword.equals("mark")) {
            System.out.printf("Nice! I've marked this task as done:%n %s%n", task.printTask());
        } else {
            System.out.printf("OK, I've marked this task as not done yet:%n %s%n", task.printTask());
        }
    }

    /**
     * Prints the deletion status of the task.
     *
     * @param task Task that was deleted.
     * @param size Updated number of tasks in the list.
     */
    public void removeItem(Task task, int size) {
        System.out.printf("Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.%n"
                , task.printTask(), size);
    }

    /**
     * Prints the addition status of the task.
     *
     * @param task Task that was added.
     * @param size Updated number of tasks in the list.
     */
    public void addItem(Task task, int size) {
        System.out.printf("Got it. I've added this task:%n %s%nNow you have %d tasks in the list.%n",
                task.printTask(), size);
    }
}
