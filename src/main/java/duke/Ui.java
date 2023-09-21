package duke;
import java.util.Scanner;


public class Ui {
    private Scanner scanner;
    //takes in user input
    public Ui() {
        scanner = new Scanner(System.in);
    }
    // ask for user input and finds the command of the user
    public String getUserInput() {
        System.out.print("Enter a command: ");
        return scanner.nextLine().trim();
    }

    // default welcome message
    public void showWelcomeMessage() {
        String name = "Johnnythesnake";
        System.out.println("Hello, I'm " + name);
        System.out.println("What can I do for you? Aside from completing your CS2103 project for you");
    }

    //Exit message when user enters bye
    public void showExitMessage() {
        System.out.println("Goodbye! Have a great day!");
    }

    // shows the user the active tasks when they ask for it
    public void showTaskList(TaskList tasks) {
        tasks.listOfTasks();
    }

    // mark task as done
    public void showMarkedAsDone(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " marked as done.");
    }

    // unmark task
    public void showUnmarked(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " unmarked.");
    }
    // shows the tasks added
    public void showTaskAdded() {
        System.out.println("Task added.");
    }

    // shows which taks has been deleted
    public void showTaskDeleted(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " deleted.");
    }

    // shows the user an error message depending on what he did wrong
    public void showError(String errorMessage) {

        System.out.println("Error: " + errorMessage);
    }

    // if issues arise from loading tasks, this message is shown
    public void showLoadingError() {
        System.out.println("Error loading tasks from storage.");
    }

    public void closeScanner() {
        scanner.close();
    }
}

