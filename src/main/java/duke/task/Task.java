package duke.task;
/*
 * Abstract class that represents a general task.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static boolean checkTaskNoDescription(String taskType, String task) {
        return taskType.equals(task);
    }

    public static boolean checkAllWhiteSpace(String s) {
        for (int i = 0; i < s.length(); i += 1) {
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public String saveStringToFile() {
        String status = isDone ? "[X]" : "[ ]";
        return " | " + status + " | " + task;
    }

    /**
     * Converts a string to a LocalDateTime object. This is for the deadline and event classes.
     * 
     * @param s the string inputted by the user in the format "2019-10-15 1800"
     * @return LocalDateTime object
     */
    public LocalDateTime convertToDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(s, formatter);
    }

    public String saveTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

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