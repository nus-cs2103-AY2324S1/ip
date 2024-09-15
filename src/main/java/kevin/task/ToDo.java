package kevin.task;

import java.time.LocalDate;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String getTaskType() {
        return "[T]";
    }

    @Override
    public String getTaskTime() {
        return "";
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (super.isDone() ? "1" : "0") + " | " + this.getTaskName();
    }

    @Override
    public LocalDate getTaskDate() {
        return null;
    }

}
