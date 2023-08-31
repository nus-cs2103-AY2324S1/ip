package duke;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        scanner.close();
    }

    public static void showLine() {
        System.out.println("-------------------------------");

    }
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showByeMessage() {
        System.out.println("Bye! Hope to see you soon!");
        showLine();
    }

    public void showLoadingError(String msg) {
        System.out.println("Failed to load existing save file due to: " + msg);
        showLine();
    }

    public void showAddTaskError(String msg) {
        System.out.println("Failed to add task because: " + msg);
        showLine();
    }

    private void showNumberOfTasks(int numTasks) {
        System.out.println("There are now " + numTasks + " tasks in the list");
        showLine();
    }

    public void showAddTaskMessage(Task task, int numTasks) {
        System.out.println("Got it! I've added this task:");
        System.out.println(task);
        showNumberOfTasks(numTasks);
    }

    public void showDeleteTaskMessage(Task task, int numTasks) {
        System.out.println("Got it! I've deleted this task:");
        System.out.println(task);
        showNumberOfTasks(numTasks);
    }

    public void showInvalidIndexError() {
        System.out.println("Invalid index provided! Make sure it is a number and within the range of number of tasks!");
        showLine();
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(task);
        showLine();
    }

    public void showInvalidCommandError() {
        System.out.println("Invalid command!");
        showLine();
    }

    public void showSaveTasksError(IOException e) {
        System.out.println("Failed to save tasks to drive: " + e.getMessage());
        showLine();
    }

    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println(taskList.toString());
        showLine();
    }
    public String readCmd() {
        // Read user input
        return scanner.nextLine();
    }

}
