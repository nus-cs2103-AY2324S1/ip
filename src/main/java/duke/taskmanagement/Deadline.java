package duke.taskmanagement;

import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Return the status icon of whether the task is done or nor
     * @return A string that represent of status of the task, [X] if done,
     * [ ] if not done.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the indication of status of the task by using
     * "1" and "0" to store it in duke.txt.
     * @return "1" if the task is done and "0" if the task is not done.
     */
    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }

    /**
     * Returns the string with the correct format to store in duke.txt
     * @return String representation of the task to store in duke.txt.
     */
    @Override
    public String saveToFileString() {
        return "D | " +  convertIsDone() + " | " + description + " | " + this.by+"\n";
    }
}
