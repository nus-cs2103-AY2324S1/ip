package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task class stores details of the tasks.
 */
public class Task {
    protected LocalDateTime byTime;
    private String details;

    private boolean isMarked;

    private String taskType;


    /**
     * Class constructor of Task.
     * @param info String that contains details of the task.
     */
    public Task(String info) {
        details = info;
        isMarked = false;
    }

    /**
     * Formats and returns time in String.
     * @return Time in String using correct format.
     */
    public String getTime() {
        return byTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
    }

    /**
     * Marks the task to be done.
     */
    public void setMarked() {
        assert !isMarked : "It is already done!";
        isMarked = true;
    }

    /**
     * Marks the task to be undone.
     */
    public void setUnmarked() {
        assert isMarked : "It is undone!";
        isMarked = false;
    }

    /**
     * Shows visual representation of status of task.
     * @return String of [X] or [].
     */
    public String showMarked() {
        String output;
        if (isMarked) {
            output = "[X] ";
        } else {
            output = "[ ] ";
        }
        return output;
    }

    /**
     * Returns the details of the task.
     * @return Details in string.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets task type.
     * @param type Type of task.
     */
    public void setTaskType(String type) {
        taskType = type;
    }

    /**
     * Returns type of task.
     * @return String which represent task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns format in which the task is saved in the hard disk.
     * @return String representation of task saved.
     */
    public String saveFormat() {
        String output;
        output = taskType + " / " + showMarked() + "/ " + details;
        return output;
    }

    /**
     * Return type, status and description of the task.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String output = "[" + taskType + "]" + showMarked() + details;
        return output;
    }
}
