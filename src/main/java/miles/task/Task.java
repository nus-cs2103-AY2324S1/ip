package miles.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import miles.MilesException;

/**
 * Represents a general task.
 */
public abstract class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructor to create a new task.
     * @param task the task
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the current task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if a task has no description by checking if the strings are equal.
     * @param taskType  the type of task (e.g. "deadline")
     * @param task      the command inputted by the user
     * @return          true if task has no description
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
     * Returns a boolean that indicates whether the task has a duplicate parameter.
     * @param s          the string inputted by the user
     * @param parameter  the parameter to be checked for duplicates
     * @return           true if the task has a duplicate parameter, false otherwise
     */
    public static boolean checkDuplicateParameter(String s, String parameter) {
        int count = 0;
        int index = 0;

        while (index != -1) {
            index = s.indexOf(parameter, index);
            if (index != -1) {
                count += 1;
                index += parameter.length();
            }
        }

        return count > 1;
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
    public LocalDateTime convertToDateTime(String s) throws MilesException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new MilesException("Please input a date and time in the following format: yyyy-MM-dd HHmm");
        }
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
