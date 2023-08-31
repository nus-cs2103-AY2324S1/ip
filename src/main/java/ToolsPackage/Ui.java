package ToolsPackage;

import TaskPackage.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String greetingString =  "Hello! I'm ChampionSOS\nWhat can I do for you?";
    private final String exitString =  "Bye. Hope to see you again soon!";
    private final String dataNotFoundString =  "No existing data found. New file created!";

    public Ui() {
    }

    public void showWelcome() {
        System.out.println(this.greetingString);
    }

    public void showBye() {
        System.out.println(this.exitString);
    }

    public void showLoadingError() {
        System.out.println(this.dataNotFoundString);
    }

    public String readCommands(Scanner inputs) {
        return inputs.nextLine();
    }

    public void printList(ArrayList<Task> listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.printf("%d.%s%n", i+1, listOfTasks.get(i).printTask());
        }
    }

    public void toggleDone(Task task, String keyword) {
        if (keyword.equals("mark")) {
            System.out.printf("Nice! I've marked this task as done:%n %s%n", task.printTask());
        } else {
            System.out.printf("OK, I've marked this task as not done yet:%n %s%n", task.printTask());
        }
    }

    public void removeItem(Task task, int size) {
        System.out.printf("Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.%n"
                , task.printTask(), size);
    }

    public void addItem(Task task, int size) {
        System.out.printf("Got it. I've added this task:%n %s%nNow you have %d tasks in the list.%n",
                task.printTask(), size);
    }

    /**
     * Prints out the list of tasks that contain the given keyword.
     *
     * @param listOfTasks List of tasks to print.
     */
    public void printMatchingTasks(ArrayList<Task> listOfTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.printf("%d.%s%n", i+1, listOfTasks.get(i).printTask());
        }
    }
}
