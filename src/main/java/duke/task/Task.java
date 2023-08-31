package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks created.
 */
public class Task {
    private boolean status;
    private String taskContent;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public Task(String reply) {
        this.status = false;
        this.taskContent = reply;
    }

    /**
     * Marks task status as done.
     */
    public void mark() {
        status = true;
    }

    /**
     * Marks task status as not done.
     */
    public void unmark() {
        status = false;
    }

    @Override
    public String toString() {
        if (status) {
            return String.format("[X] %s", taskContent);
        } else {
            return String.format("[ ] %s", taskContent);
        }
    }

    /**
     * Shows the content of task.
     * @return
     */
    public String showContent() {
        return taskContent;
    }

    /**
     * Constructs the string of a task to be saved into file.
     * @return
     */
    public String saveToFileLine() {
        return String.format("%s | %s", status ? "1" : "0", taskContent);
    }
}
