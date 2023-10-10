package skye.ui;

import java.time.LocalDate;
import java.util.List;

import skye.data.exception.DukeExceptionType;
import skye.data.task.Task;
import skye.data.venue.Venue;

/**
 * Represents the command line user interface which consists of a scanner to read in
 * user input and methods to display different messages to be shown to the user.
 */
public class UI {

    /**
     * Display the welcome message used at the start of the program.
     */
    public static String showWelcome() {
        String message = "Hello! I'm Skye, your personal task assistant, "
                + "ready to help you conquer your to-do list and vanquish procrastination.\n"
                + "How may I be of service to you today?";
        return message;
    }

    /**
     * Display the exit message shown when exiting the program.
     *
     * @return Farewell message
     */
    public String showGoodBye() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Display the error message when reading or writing to the save file has failed.
     */
    public String showLoadingError() {
        return DukeExceptionType.SAVE_FILE_LOAD_FAILED.getMessage();
    }

    /**
     * Display the current list of tasks that the user has recorded.
     *
     * @param tasks A list of tasks from the TaskList
     */
    public String showTasks(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Here are the tasks in your list:")
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                stringBuilder
                        .append(String.format("%d. %s", i + 1, tasks.get(i)))
                        .append(System.lineSeparator());
            }
        } else {
            stringBuilder
                    .append("Amazing!! You're all caught up! Enjoy your day :)")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Display the task that the user has recently added.
     *
     * @param task Task that was added to the task list
     * @param tasks Task list
     */
    public String showAddedTask(Task task, List<Task> tasks) {
        return String.format(
                "Got it. I've added the following task:\n %s\nNow you have %d task(s) in the list.",
                task.toString(),
                tasks.size()
        );
    }

    /**
     * Display the task that the user has recently deleted.
     *
     * @param task Task that was deleted from the task list
     * @param tasks Task list
     */
    public String showRemovedTask(Task task, List<Task> tasks) {
        return String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                task.toString(),
                tasks.size()
        );
    }

    /**
     * Display the task that was recently marked as completed.
     *
     * @param task Task marked as completed
     */
    public String showMarkedTask(Task task) {
        return String.format("Good job! I've marked this task as done:\n %s", task);
    }

    /**
     * Displays the task that was recently unmarked as incomplete.
     *
     * @param task Task unmarked as incomplete
     */
    public String showUnmarkedTask(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n %s", task);
    }

    /**
     * Displays the list of tasks due on a specified date
     *
     * @param date Due date
     * @param tasks Task list
     */
    public String showTasksDueOn(LocalDate date, List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Here are the tasks due on: ")
                .append(date)
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            tasks.forEach(task -> stringBuilder.append(task).append(System.lineSeparator()));
        } else {
            stringBuilder
                    .append("Great!! You've nothing due to worry about!")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Displays a help guide for the user.
     *
     * @param lines Lines from the help guide
     */
    public String showHelpMessage(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Displays a message when an unrecognized command is typed and
     * refers the user to the help command
     *
     * @return Invalid command message
     */
    public String showInvalidCommandMsg() {
        return DukeExceptionType.UNKNOWN_COMMAND.getMessage();
    }

    /**
     * Display a list of matching tasks on the command line interface.
     *
     * @param tasks A list of matching tasks found
     * @return The message replied by the chatbot.
     */
    public String showFoundTasks(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Here are the matching tasks in your list:")
                .append(System.lineSeparator());
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                stringBuilder
                        .append(String.format("%d.%s%n", i + 1, tasks.get(i)))
                        .append(System.lineSeparator());
            }
        } else {
            stringBuilder
                    .append("No matching tasks found :(")
                    .append(System.lineSeparator());
        }

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
                "Got it. I've added this venue:\n %s\nNow you have %d venue(s) in the list.",
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
                        .append(String.format("%d.%s", i + 1, venues.get(i)))
                        .append(System.lineSeparator());
            }
        } else {
            stringBuilder
                    .append("No matching venues found :(")
                    .append(System.lineSeparator());
        }

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
                "Noted. I've removed this venue:\n %s\nNow you have %d venue(s) in the list",
                removedVenue,
                venues.size()
        );
    }
}
