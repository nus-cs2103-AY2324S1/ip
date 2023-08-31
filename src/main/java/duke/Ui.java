package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Read a line of input from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    // Show a welcome message when the program starts
    public void showWelcome() {
        printLine("____________________________________________________________");
        printLine("Hello! I'm duke.Duke");
        printLine("What can I do for you?");
        printLine("____________________________________________________________");
    }

    // Show a goodbye message when the program ends
    public void showGoodbye() {
        printLine("____________________________________________________________");
        printLine("Bye. Hope to see you again soon!");
        printLine("____________________________________________________________");
    }

    // Show an error message
    public void showError(String message) {
        printLine("____________________________________________________________");
        printLine("☹ OOPS!!! " + message);
        printLine("____________________________________________________________");
    }

    // Show a loading error specifically
    public void showLoadingError() {
        showError("There was an error loading saved tasks.");
    }

    // Generic method to print a message surrounded by lines
    public void printMessage(String message) {
        printLine("____________________________________________________________");
        printLine(message);
        printLine("____________________________________________________________");
    }

    // Helper method to print a line
    private void printLine(String line) {
        System.out.println(line);
    }

    public void showTasksList(TaskList tasks) {
        printLine("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            printLine((i + 1) + "." + task);
        }
    }
    public void showAddedTask(Task task, int totalTasks) {
        printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int totalTasks) {
        printMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showSavingError(String errorMessage) {
        printLine("Error saving tasks: " + errorMessage);
    }

    public void showInvalidTaskNumber() {
        printMessage("Please provide a valid task number.");
    }

    public void showTaskMarked(Task task) {
        printLine("____________________________________________________________");
        printLine("Nice! I've marked this task as done:");
        printLine("  [" + task.getStatusIcon() + "] " + task.description);
        printLine("____________________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        printLine("____________________________________________________________");
        printLine("OK, I've marked this task as not done yet:");
        printLine("  [" + task.getStatusIcon() + "] " + task.description);
        printLine("____________________________________________________________");
    }
}
