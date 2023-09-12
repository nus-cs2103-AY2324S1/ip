package duke;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        System.out.print("Enter a command: ");
        return scanner.nextLine().trim();
    }

    public void showWelcomeMessage() {
        String name = "Johnnythesnake";
        System.out.println("Hello, I'm " + name);
        System.out.println("What can I do for you? Aside from completing your CS2103 project for you");
    }

    public void showExitMessage() {
        System.out.println("Goodbye! Have a great day!");
    }

    public void showTaskList(TaskList tasks) {
        tasks.listOfTasks();
    }

    public void showMarkedAsDone(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " marked as done.");
    }

    public void showUnmarked(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " unmarked.");
    }

    public void showTaskAdded() {
        System.out.println("Task added.");
    }

    public void showTaskDeleted(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " deleted.");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from storage.");
    }
    public void closeScanner() {
        scanner.close();
    }
}

