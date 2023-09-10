package koko;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final String name;
    private final Scanner scanner;

    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    private void printFormatted(String originalMessage) {
        String indentedMessage = Arrays.stream(originalMessage.split("\n"))
                .map(line -> "     " + line)
                .collect(Collectors.joining("\n"));

        String formattedMessage = String.format("    %s\n%s\n    %s",
                HORIZONTAL_LINE, indentedMessage, HORIZONTAL_LINE);

        System.out.println(formattedMessage);
    }

    public void startUserInputLoop(Consumer<String> parseInputAndDispatch) {
        Stream.generate(scanner::nextLine)
                .takeWhile(input -> !input.equals("bye"))
                .forEach(parseInputAndDispatch);
    }

    public void greet() {
        printFormatted("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    public void exit() {
        printFormatted("Bye. Hope to see you again soon!");
    }


    public void printTaskAddedMessage(Task task, int taskCount) {
        printFormatted(String.format("Got it. I've added this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    public void printTaskDeletedMessage(Task task, int taskCount) {
        printFormatted(String.format("Noted. I've removed this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    public void printTaskMarkedMessage(Task task) {
        printFormatted(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
    }

    public void printTaskUnmarkedMessage(Task task) {
        printFormatted(String.format("Nice! I've unmarked this task as done:\n  %s", task.toString()));
    }

    public void printTaskList(TaskList taskList) {
        printFormatted(taskList.toStringForUi());
    }

    public void printErrorMessage(String errorMessage) {
        printFormatted(String.format("Error: %s", errorMessage));
    }

    public void showLoadedTasks(TaskList taskList) {
        printFormatted("Loaded tasks from file:\n" + taskList.toStringForUi());
    }

    /**
     * Displays a list of tasks that match a keyword.
     * @param matchingTasks The list of tasks that match a keyword.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        printFormatted("Here are the matching tasks in your list:\n" + matchingTasks.toStringForUi());
    }


}
