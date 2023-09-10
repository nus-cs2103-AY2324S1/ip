package miles.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task.
 */
public abstract class Task {
    private boolean isDone;
    private String task;

    /**
     * Constructor to create a new task.
     * @param task the task
     */
    public Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if a task has no description by checking if the strings are equal.
     * @param taskType  the type of task (e.g. "deadline")
     * @param task      the command inputted by the user
     * @return          a boolean that indicates whether the task has no description
     */
    public static boolean checkTaskNoDescription(String taskType, String task) {
        return taskType.equals(task);
    }

    /**
     * Checks if a given string is all whitespace.
     * @param s string to be checked
     * @return  boolean that indicates whether the string is all whitespace
     */
    public static boolean checkAllWhiteSpace(String s) {
        for (int i = 0; i < s.length(); i += 1) {
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Provides a string that contains the status and task to be saved in a file.
     * @return string that contains the status and task to be saved in a file
     */
    public String saveStringToFile() {
        String status = isDone ? "[X]" : "[ ]";
        return " | " + status + " | " + task;
    }

    /**
     * Converts a string to a LocalDateTime object. This is for the deadline and event classes.
     * @param s the string inputted by the user in the format "2019-10-15 1800"
     * @return  LocalDateTime object
     */
    public LocalDateTime convertToDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(s, formatter);
    }

    /**
     * Returns a string to be saved in the text file.
     * @return a string to be saved in the text file
     */
    public String saveTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    /**
     * Returns a string representing the time to be displayed in the UI.
     * @return string representing the time to be displayed in the UI
     */
    public String displayTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + task;
    }
}
