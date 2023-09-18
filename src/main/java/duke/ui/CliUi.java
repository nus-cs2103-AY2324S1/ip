package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Helper class for user interface, handles
 * user input and outputting to the user.
 */
public class CliUi implements Ui {
    private static final String DOTTED_LINE = "____________________________________________________________";

    private final String name;

    private final Scanner scanner;

    /**
     * Initialise Ui handler.
     * @param name Name of chatbot.
     */
    public CliUi(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void init() {
        System.out.println(CliUi.DOTTED_LINE);
        System.out.printf("Hello! I'm %s\n", this.name);
        System.out.println("What can I do for you?");
        System.out.println(CliUi.DOTTED_LINE);
    }

    @Override
    public String getInput() {
        System.out.print("You:  ");
        return scanner.nextLine();
    }

    @Override
    public void printException(Exception e) {
        System.out.printf("[!] %s\n", e.getMessage());
    }

    @Override
    public void exit() {
        System.out.println(CliUi.DOTTED_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(CliUi.DOTTED_LINE);
    }

    @Override
    public void addTask(Task task) {
        System.out.printf("%s: [Added] %s\n", this.name, task);
    }

    @Override
    public void deleteTask(Task task) {
        System.out.printf("%s: [Deleted] %s\n", this.name, task);
    }

    @Override
    public void markTask(Task task) {
        System.out.printf("%s: I've marked this task as done.\n", this.name);
        System.out.printf("    %s\n", task);
    }

    @Override
    public void unmarkTask(Task task) {
        System.out.printf("%s: I've marked this task as not done.\n", this.name);
        System.out.printf("    %s\n", task);
    }

    @Override
    public void listTasks(List<Task> tasks) {
        System.out.printf("%s: \n", this.name);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s\n", i + 1, tasks.get(i));
        }
    }

    @Override
    public void invalidCommand(String command) {
        System.out.printf("%s: Invalid command (%s)\n", this.name, command);
    }
}
