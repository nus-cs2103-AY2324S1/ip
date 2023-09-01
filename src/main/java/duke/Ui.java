package duke;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(" -------------------------------------------------------------------");
    }
    public void showWelcome() {
        showLine();
        System.out.println("  Hello! I'm Handsome!\n  What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println(" Something went wrong when loading tasks");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("  Bye. Hope to see you again soon!");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        int index = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.printf("  %d. %s\n", index, task.toString());
            index++;
        }
    }
}
