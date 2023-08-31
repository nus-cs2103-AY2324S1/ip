package rocket;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        showError("Files were unable to load");
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Rocket");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out a nicely-formatted error message
     * @param message the error message.
     */
    public void showError(String message) {
        showLine();
        System.out.println("     ☹ OOPS!!! " + message);
        showLine();
    }

    public void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("    " + (i + 1) + "." + task);
        }
    }
}
