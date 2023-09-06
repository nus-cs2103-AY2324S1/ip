package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates different types of tasks that have a name and whether it is done.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String markAsDone() {
        this.isDone = true;
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(this.toString() + "\n");
        return output.toString();
    }

    public String markAsUndone() {
        this.isDone = false;
        StringBuilder output = new StringBuilder("OK, I've marked this task as not done yet:\n");
        output.append(this.toString() + "\n");
        return output.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    /**
     * Generates task list entry in specified format.
     * @return task list entry as a string
     */
    public abstract String toTaskListEntry();
}
