package skye.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import skye.data.task.Task;
import skye.data.venue.Venue;

/**
 * Represents the command line user interface which consists of a scanner to read in
 * user input and methods to display different messages to be shown to the user.
 */
public class UI {
    private static final int LINE_LENGTH = 100;
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to be shown to the user with horizontal lines
     * before and after the message.
     *
     * @param message Message shown to the user
     */
    public void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }

    /**
     * Reads the command from the user's input.
     *
     * @return A command from the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Renders a horizontal line on the terminal.
     */
    public void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    /**
     * Display the welcome message used at the start of the program.
     *
     */
    public void showWelcome() {
        String message = "Hello! I'm Skye, your personal task assistant.\n"
                + "What can I do for you?";
        printMessage(message);
    }

    /**
     * Display the exit message shown when exiting the program.
     *
     * @return Farewell message
     */
    public String showGoodBye() {
        scanner.close();
        String message = "Bye. Hope to see you again soon!";
        printMessage(message);
        return message;
    }

    /**
     * Display the error message when writing to the save file has failed.
     */
    public void showLoadingError() {
        printMessage("Sorry! I was unable to load the save file :(");
    }

    /**
     * Display the current list of tasks that the user has recorded.
     *
     * @param tasks A list of tasks from the TaskList
     */
    public String showTasks(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        renderLine();
        System.out.println("Here are the tasks in your list:");
        stringBuilder
                .append("Here are the tasks in your list:")
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                stringBuilder
                        .append(String.format("%d. %s", i + 1, tasks.get(i)))
                        .append(System.lineSeparator());
            }
        } else {
            System.out.println("Nice!! You're all caught up and have no pending tasks to worry about.");
            stringBuilder
                    .append("Nice!! You're all caught up and have no pending tasks to worry about.")
                    .append(System.lineSeparator());
        }
        renderLine();
        return stringBuilder.toString();
    }

    /**
     * Display the task that the user has recently added.
     *
     * @param task Task that was added to the task list
     * @param tasks Task list
     */
    public String showAddedTask(Task task, List<Task> tasks) {
        String message = String.format(
                "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list.",
                task.toString(),
                tasks.size()
        );
        printMessage(message);
        return message;
    }

    /**
     * Display the task that the user has recently deleted.
     *
     * @param task Task that was deleted from the task list
     * @param tasks Task list
     */
    public String showRemovedTask(Task task, List<Task> tasks) {
        String message = String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                task.toString(),
                tasks.size()
        );
        printMessage(message);
        return message;
    }

    /**
     * Display the task that was recently marked as completed.
     *
     * @param task Task marked as completed
     */
    public String showMarkedTask(Task task) {
        String message = String.format("Nice! I've marked this task as done:\n %s", task);
        printMessage(message);
        return message;
    }

    /**
     * Displays the task that was recently unmarked as incomplete.
     *
     * @param task Task unmarked as incomplete
     */
    public String showUnmarkedTask(Task task) {
        String message = String.format("OK, I've marked this task as not done yet:\n %s", task);
        printMessage(message);
        return message;
    }

    /**
     * Displays the list of tasks due on a specified date
     *
     * @param date Due date
     * @param tasks Task list
     */
    public String showTasksDueOn(LocalDate date, List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        renderLine();
        System.out.println("Here are the tasks due on: " + date.toString());
        stringBuilder
                .append("Here are the tasks due on: ")
                .append(date)
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            tasks.forEach(System.out::println);
            tasks.forEach(task -> stringBuilder.append(task).append(System.lineSeparator()));
        } else {
            System.out.println("Great!! You've nothing due!");
            stringBuilder
                    .append("Great!! You've nothing due!")
                    .append(System.lineSeparator());
        }
        renderLine();
        return stringBuilder.toString();
    }

    /**
     * Displays a help guide for the user.
     *
     * @param lines Lines from the help guide
     */
    public String showHelpMessage(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        renderLine();
        for (String line : lines) {
            System.out.println(line);
            stringBuilder.append(line).append(System.lineSeparator());
        }
        renderLine();
        return stringBuilder.toString();
    }

    /**
     * Displays a message when an unrecognized command is typed and
     * refers the user to the help command
     *
     * @return Invalid command message
     */
    public String showInvalidCommandMsg() {
        String message = "I'm sorry, I don't know what that means :-(";
        printMessage(message);
        return message;
    }

    /**
     * Display a list of matching tasks on the command line interface.
     *
     * @param tasks A list of matching tasks found
     * @return The message replied by the chatbot.
     */
    public String showFoundTasks(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        renderLine();
        System.out.println("Here are the matching tasks in your list:");
        stringBuilder
                .append("Here are the matching tasks in your list:")
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                stringBuilder
                        .append(String.format("%d.%s%n", i + 1, tasks.get(i)))
                        .append(System.lineSeparator());
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        } else {
            stringBuilder
                    .append("No matching tasks found :(")
                    .append(System.lineSeparator());
            System.out.println("No matching tasks found :(");
        }
        renderLine();

        return stringBuilder.toString();
    }


    /**
     * Display the current list of venues that the user has recorded.
     *
     * @param venues Venue list
     * @return The list of tasks in chat format.
     */
    public String showVenues(List<Venue> venues) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Here are the venues in your list:")
                .append(System.lineSeparator());
        if (!venues.isEmpty()) {
            for (int i = 0; i < venues.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, venues.get(i));
                stringBuilder
                        .append(String.format("%d. %s", i + 1, venues.get(i)))
                        .append(System.lineSeparator());
            }
        } else {
            stringBuilder
                    .append("You've currently no venues in your list.")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Displays the venue that was added by the user.
     *
     * @param venue Venue added by the user
     * @param venues Venue list
     * @return The recently added venue in chat format.
     */
    public String showAddedVenue(Venue venue, List<Venue> venues) {
        return String.format(
                "Got it. I've added this venue:\n %s\nNow you have %d task(s) in the list.",
                venue,
                venues.size()
        );
    }

    /**
     * Display a list of matching venues on the command line interface.
     *
     * @param venues Venue list
     * @return A list of venues in chat format.
     */
    public String showFoundVenues(List<Venue> venues) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Here are the matching venues in your list:")
                .append(System.lineSeparator());
        if (!venues.isEmpty()) {
            for (int i = 0; i < venues.size(); i++) {
                stringBuilder
                        .append(String.format("%d.%s%n", i + 1, venues.get(i)))
                        .append(System.lineSeparator());
                System.out.printf("%d.%s%n", i + 1, venues.get(i));
            }
        } else {
            stringBuilder
                    .append("No matching venues found :(")
                    .append(System.lineSeparator());
        }
        renderLine();

        return stringBuilder.toString();
    }

    /**
     * Displays the venue that was removed by the user.
     *
     * @param removedVenue The venue that was removed by the user.
     * @param venues Venue list.
     * @return The venue removed by the user in chat form.
     */
    public String showRemovedVenue(Venue removedVenue, List<Venue> venues) {
        return String.format(
                "Noted. I've removed this venue:\n %s\nNow you have %d tasks in the list",
                removedVenue,
                venues.size()
        );
    }
}
