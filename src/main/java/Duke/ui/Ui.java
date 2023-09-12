package Duke.ui;

import java.util.Scanner;

import Duke.tasks.Task;
import Duke.tasks.TaskList;

public class Ui {
    private static final String horizontal_line = "_________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        handleStart();
    }

    public String readUserCommand() {
        return scanner.nextLine();
    }

      /**
     * Initialises and displays the welcome message for the Duke application.
     */
    public static void handleStart() {
        String welcomeMessage = horizontal_line +
                "\nHello! I'm Blob\n" +
                "What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
    }

    public void showWelcome() {
        String welcomeMessage = horizontal_line +
                                " Hello! I'm Blob\n" +
                                " What can I do for you?\n" +
                                horizontal_line;
        System.out.println(welcomeMessage);
    }

    public void showGoodbye() {
        scanner.close();
        System.out.println(horizontal_line);
        System.out.println("Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTasks(TaskList tasks) {
        System.out.println(tasks.size());
        System.out.println(horizontal_line);
        System.out.println("here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            System.out.println((i + 1) + ". " + task);
        }
        System.out.println(horizontal_line);
    }

    public void showMessage(String message) {
        System.out.println(message + "\n" + horizontal_line);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    public void showAddTask(Task currTask) {
        System.out.println("Got it I have added this task:" + "\n" + currTask + "\n" + horizontal_line);
    }
}
