package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Helper class for user interface, handles
 * user input and outputting to the user.
 */
public class Ui {
    private final String name;

    private final Scanner scanner;

    /**
     * Initialise duke.Ui handler.
     * @param name Name of chatbot.
     */
    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initialise UI. Prints banner.
     */
    public void init() {
        System.out.println("____________________________________________________________");
        System.out.printf("Hello! I'm %s\n", this.name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prompts user and parses user input.
     * @return duke.Parser instance for user input.
     * @throws DukeException If error in parsing user input.
     */
    public Parser getParsedInput() throws DukeException {
        System.out.print("You:  ");
        String userInput = scanner.nextLine();
        return Parser.from(userInput);
    }

    /**
     * Prints exception to user.
     * @param e Exception to display.
     */
    public void printException(Exception e) {
        System.out.printf("[!] %s\n", e.getMessage());
    }

    /**
     * Prints exit message to user.
     */
    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints add task message to user.
     * @param task duke.Task added.
     */
    public void addTask(Task task) {
        System.out.printf("%s: [Added] %s\n", this.name, task);
    }

    /**
     * Prints delete task message to user.
     * @param task duke.Task deleted.
     */
    public void deleteTask(Task task) {
        System.out.printf("%s: [Deleted] %s\n", this.name, task);
    }

    /**
     * Prints mark task message to user.
     * @param task duke.Task marked.
     */
    public void markTask(Task task) {
        System.out.printf("%s: I've marked this task as done.\n", this.name);
        System.out.printf("    %s\n", task);
    }

    /**
     * Prints unmark task message to user.
     * @param task duke.Task unmarked.
     */
    public void unmarkTask(Task task) {
        System.out.printf("%s: I've marked this task as not done.\n", this.name);
        System.out.printf("    %s\n", task);
    }

    /**
     * Lists all current tasks to user.
     * @param tasks Tasks to list.
     */
    public void listTasks(List<Task> tasks) {
        System.out.printf("%s: \n", this.name);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Prints invalid command message.
     * @param command Command used.
     */
    public void invalidCommand(String command) {
        System.out.printf("%s: Invalid command (%s)\n", this.name, command);
    }
}
