package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents the user interface of the program.
 */
public class Ui {

    private final String name;
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public String getHelloMessage() {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Hello! I'm " + this.name + "\n");
        output.append("What can I do for you?\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints dotted line with new line.
     */
    public static String getDottedLine() {
        return "________________________\n";
    }

    /**
     * Prints the goodbye message.
     * @return the goodbye message
     */
    public static String getGoodbyeMessage() {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Bye. Hope to see you again soon!\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the delete message.
     * @param tasks the list of tasks
     * @param index the index of the task to be deleted
     * @return the delete message
     */
    public static String getDeleteTaskMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Noted. I've removed this duke.task:\n");
        output.append(tasks.get(index - 1) + "\n");
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        int remainingTasks = tasks.size() - 1;
        output.append("Now you have " + remainingTasks + " " + placeholder + " in the list.\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the add task message.
     * @param tasks the list of tasks
     * @return the add task message
     */
    public static String getAddTaskMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Got it. I've added this duke.task:\n");
        output.append(tasks.get(tasks.size() - 1) + "\n");
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        output.append("Now you have " + tasks.size() + " " + placeholder + " in the list.\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the list tasks message.
     * @param tasks the list of tasks
     * @return the list tasks message
     */
    public static String getListTasksMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        if (tasks.isEmpty()) {
            output.append("There are no tasks in your list.\n");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1) + "." + tasks.get(i) + "\n");
            }
        }
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the tasklist in sorted order within Todos, Deadlines, and Events.
     * @param tasks the list of tasks
     * @return the tasklist in sorted order
     */
    public static String getSortedTasksByTypeMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());

        if (tasks.isEmpty()) {
            output.append("There are no tasks in your list.\n");
        } else {
            // Separate the tasks by type
            List<Task> todoTasks = tasks.stream().filter(task ->
                    task.toString().startsWith("[T]")).collect(Collectors.toList());
            List<Task> deadlineTasks = tasks.stream().filter(task ->
                    task.toString().startsWith("[D]")).sorted(compareByDateTime()).collect(Collectors.toList());
            List<Task> eventTasks = tasks.stream().filter(task ->
                    task.toString().startsWith("[E]")).sorted(compareByDateTime()).collect(Collectors.toList());
            output.append("Here are the tasks in your list:\n");
            int index = 1;
            for (Task t : todoTasks) {
                output.append(index++ + "." + t + "\n");
            }
            for (Task t : deadlineTasks) {
                output.append(index++ + "." + t + "\n");
            }
            for (Task t : eventTasks) {
                output.append(index++ + "." + t + "\n");
            }
        }
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the tasklist in sorted order by datetime.
     * @param tasks the list of tasks
     * @return the tasklist in sorted order
     */
    public static String getSortedTasksByDatetimeMessage(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        List<Task> todoTasks = tasks.stream().filter(task ->
                task.toString().startsWith("[T]")).collect(Collectors.toList());
        if (tasks.isEmpty()) {
            output.append("There are no tasks in your list.\n");
        } else {
            output.append("Here are the tasks in your list:\n");
            int index = 1;
            for (Task t : todoTasks) {
                output.append(index++ + "." + t + "\n");
            }
            List<Task> sortedDeadlinesAndEvents = getSortedDeadlinesAndEvents(tasks);
            for (Task t : sortedDeadlinesAndEvents) {
                output.append(index++ + "." + t + "\n");
            }
        }
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Returns a list of deadlines and events sorted by date-time.
     * @param tasks the list of tasks
     * @return the list of deadlines and events sorted by date-time
     */
    public static List<Task> getSortedDeadlinesAndEvents(List<Task> tasks) {
        // Separate the tasks by type
        List<Task> nonTodoTasks = tasks.stream()
                .filter(task -> task.toString().startsWith("[D]") || task.toString().startsWith("[E]"))
                .collect(Collectors.toList());
        nonTodoTasks.sort(compareByDateTime());
        return nonTodoTasks;
    }

    /**
     * Provides a comparator to sort tasks by date-time.
     * @return the comparator to sort tasks by date-time.
     */
    private static Comparator<Task> compareByDateTime() {
        return (task1, task2) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTime1 = extractDateTimeFromTask(task1, formatter);
            LocalDateTime dateTime2 = extractDateTimeFromTask(task2, formatter);
            return dateTime1.compareTo(dateTime2);
        };
    }

    /**
     * Extracts date-time from a task.
     * @param task the task
     * @param formatter the date-time formatter
     * @return the date-time of the task
     */
    private static LocalDateTime extractDateTimeFromTask(Task task, DateTimeFormatter formatter) {
        String taskStr = task.toString();
        String dateStr;

        if (taskStr.startsWith("[D]")) {
            dateStr = taskStr.split("by: ")[1].split("\\)")[0].trim();
        } else { // for [E] type
            dateStr = taskStr.split("from: ")[1].split(" to:")[0].trim();
        }

        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * Prints the done task message.
     * @param tasks the list of tasks
     * @param index the index of the completed task
     * @return the done task message
     */
    public static String getMarkAsDoneMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("Nice! I've marked this task as done:\n");
        tasks.get(index - 1).markAsDone();
        output.append(tasks.get(index - 1) + "\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Prints the undone task message.
     * @param tasks the list of tasks
     * @param index the index of the uncompleted task
     * @return the undone task message
     */
    public static String getMarkAsUndoneMessage(List<Task> tasks, int index) {
        StringBuilder output = new StringBuilder();
        output.append(getDottedLine());
        output.append("OK, I've marked this task as not done yet:\n");
        tasks.get(index - 1).markAsUndone();
        output.append(tasks.get(index - 1) + "\n");
        output.append(getDottedLine());
        return output.toString();
    }

    /**
     * Shows the error message when saving file.
     * @return the error message when saving file
     */
    public static String getErrorSavingToFileMessage() {
        return "Error saving data to file.";
    }

    /**
     * Shows the error message when loading file.
     * @return the error message when loading file
     */
    public static String getErrorLoadingFromFileMessage() {
        return "Error loading data from file.";
    }


}
