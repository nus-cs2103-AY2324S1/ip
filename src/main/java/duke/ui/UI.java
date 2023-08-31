package duke.ui;

import duke.data.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static final int LINE_LENGTH = 100;
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    public void showWelcome() {
        printMessage("Hello! I'm Skye, your personal task assistant.\n"
                + "What can I do for you?");
    }

    public void showGoodBye() {
        scanner.close();
        printMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        printMessage("Sorry! I was unable to load the save file :(");
    }

    public void showTasks(List<Task> tasks) {
        renderLine();
        System.out.println("Here are the tasks in your list:");
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("Nice!! You're all caught up and have no pending tasks to worry about.");
        }
        renderLine();
    }

    public void showAddedTask(Task task, List<Task> tasks) {
        printMessage(
            String.format(
                    "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list.",
                    task.toString(),
                    tasks.size()
            )
        );
    }

    public void showRemovedTask(Task task, List<Task> tasks) {
        printMessage(
            String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                task.toString(),
                tasks.size()
            )
        );
    }

    public void showMarkedTask(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n %s", task));
    }

    public void showUnmarkedTask(Task task) {
        printMessage(String.format("OK, I've marked this task as not done yet:\n %s", task));
    }

    public void showTasksDueOn(LocalDate date, List<Task> tasks) {
        renderLine();
        System.out.println("Here are the tasks due on: " + date.toString());
        if (!tasks.isEmpty()) {
            tasks.forEach(System.out::println);
        } else {
            System.out.println("Great!! You've nothing due!");
        }
        renderLine();
    }

    /**
     * Displays a help guide for the user.
     *
     * @param lines Lines from the help guide
     */
    public void showHelpMessage(List<String> lines) {
        renderLine();
        for (String line : lines) {
            System.out.println(line);
        }
        renderLine();
    }

    public void showInvalidCommandMsg() {
        printMessage("I'm sorry, I don't know what that means :-(");
    }

    /**
     * Display a list of matching tasks on the command line interface.
     *
     * @param tasks A list of matching tasks found
     */
    public void showFoundTasks(List<Task> tasks) {
        renderLine();
        System.out.println("Here are the matching tasks in your list:");
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("No matching tasks found :(");
        }
        renderLine();
    }
}
