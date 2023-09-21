package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Class for tasks created.
 */
public class Task {
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private boolean isDone;
    private String taskContent;

    /**
     * Constructs Task object.
     * @param reply
     */
    public Task(String reply) {
        this.isDone = false;
        this.taskContent = reply;
    }

    /**
     * Marks task isDone as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks task isDone as not done.
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
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
        return String.format("%s | %s", isDone ? "1" : "0", taskContent);
    }
}
