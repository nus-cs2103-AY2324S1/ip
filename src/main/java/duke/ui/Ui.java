package duke.ui;

import duke.tasks.Task;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Ui {
    private static final String LINE = "    __________________________________________";
    private final Scanner s = new Scanner(System.in);

    public String readCommand() {
        return s.nextLine();
    }

    public void showSuccessMark(boolean isMark, Task task) {
        this.formatLines(() -> {
            if (isMark) {
                System.out.println("    Nice! I've marked this task as done:");
            } else {
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.println("      " + task);
        });
    }

    public void showAddTask(Task task, int totalTasks) {
        this.formatLines(() -> {
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        });
    }

    public void showNoTasks() {
        this.formatLines(() -> System.out.println("    No tasks found!"));
    }

    public void showTasks(List<Task> tasks, boolean isFiltered) {
        this.formatLines(() -> {
            String header = isFiltered ? "Here are the matching tasks in your list:" : "Here are the tasks in your list:";
            System.out.println("    " + header);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(String.format("    %s.%s", i + 1, task));
            }
        });
    }

    public void showSuccessDelete(Task t, int totalTasks) {
        this.formatLines(() -> {
            System.out.println("    Noted. I've removed this task:");
            System.out.println("    " + t);
            System.out.println("    Now you have " + totalTasks + " tasks in the list.");
        });
    }

    public void showError(String message) {
        this.formatLines(() -> System.out.println("    " + message));
    }

    public void showUnknownCommand() {
        this.formatLines(() -> System.out.println("    Please enter something :-)"));
    }

    public void showSuccessLoadingStorage(String filepath) {
        this.formatLines(() -> System.out.println("    Data has been restored from " + filepath));
    }

    public void showErrorFileNotFound() {
        this.formatLines(() -> System.out.println("    Data file not found, creating a new one"));
    }

    public void showErrorLoadingFile() {
        this.formatLines(() -> System.out.println("    Error creating new file, quitting program now..."));
    }

    public void printGreetings() {
        this.formatLines(() -> {
            System.out.println("    Hello I'm lynn the koala <3");
            System.out.println("    What can I do for you?");
        });
    }

    public void printExit() {
        this.formatLines(() -> System.out.println("    Bye. Hope to see you again soon!"));
    }

    private void formatLines(Runnable runnable) {
        System.out.println(LINE);
        runnable.run();
        System.out.println(LINE);
    }
}
