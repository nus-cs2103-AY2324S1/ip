package duke.ui;

import duke.data.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "\t____________________________________________________________";

    private final String botName;
    private final Scanner scanner;

    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    public String getUserCommand() {
        String command = scanner.nextLine();
        while (command.trim().isEmpty()) {
            command = scanner.nextLine();
        }
        return command;
    }

    public void showMessage(String... message) {
        System.out.println(LINE);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + LINE + "\n");
    }

    public void showErrorMessage(String message) {
        showMessage("☹ OOPS!!! " + message);
    }

    public void showWelcomeMessage() {
        showMessage("Hello! I'm " + botName, "What can I do for you?");
    }

    public void showExitMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showErrorMessage("There was an error in loading the existing tasks.");
    }

    public void showList(List<? extends Task> tasks) {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println("\n" + LINE + "\n");
    }

    /**
     * Prints the filtered list of tasks as a formatted indexed list.
     *
     * @param tasks The filtered list of tasks to be printed.
     */
    public void showFilteredList(List<? extends Task> tasks) {
        System.out.println(LINE);

        if (tasks.size() == 0) {
            System.out.println("\t There are no matching tasks in your list.");
        } else {
            System.out.println("\t Here are the matching tasks in your list:");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println("\n" + LINE + "\n");
    }
}
