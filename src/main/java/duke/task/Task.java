package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Abstract class to represent a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    /** Enum to represent the type of task to be added. */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     * @param isCompleted Whether the task is completed.
     */
    protected Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /** Returns a string representation of the task to be stored in the data file. */
    public abstract String getDataString();

    protected String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    protected String getDescription() {
        return this.description;
    }

    protected String formatDateTime(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy, KK:mma");
            return formatter.format(temporalAccessor);
        }

        if (temporalAccessor instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy");
            return formatter.format(temporalAccessor);
        }

        return "";
    }

    /**
     * Marks the task as done.
     *
     * @return The task itself.
     */
    public Task markAsDone() {
        this.isCompleted = true;
        return this;
    }

    /**
     * Marks the task as not done.
     *
     * @return The task itself.
     */
    public Task markAsUndone() {
        this.isCompleted = false;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    /**
     * Compares two tasks by their description.
     */
    public static int compareByName(Task task1, Task task2) {
        return task1.description.compareTo(task2.description);
    }

    /**
     * Compares two tasks by their type.
     * Todo < Deadline < Event
     */
    public static int compareByType(Task task1, Task task2) {
        if (task1 instanceof Todo && task2 instanceof Todo) {
            return 0;
        } else if (task1 instanceof Todo) {
            return -1;
        } else if (task2 instanceof Todo) {
            return 1;
        }

        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            return 0;
        } else if (task1 instanceof Deadline) {
            return -1;
        } else if (task2 instanceof Deadline) {
            return 1;
        }

        if (task1 instanceof Event && task2 instanceof Event) {
            return 0;
        } else if (task1 instanceof Event) {
            return -1;
        } else if (task2 instanceof Event) {
            return 1;
        }

        return 0;
    }

    /**
     * Compares two tasks by their completion status.
     */
    public static int compareByCompletion(Task task1, Task task2) {
        return Boolean.compare(task1.isCompleted, task2.isCompleted);
    }

    /**
     * Compares two tasks by their date.
     */
    public static int compareByDate(Task task1, Task task2) {
        if (task1 instanceof Todo && task2 instanceof Todo) {
            return 0;
        }

        if (task1 instanceof Event && task2 instanceof Event) {
            return 0;
        }

        if (task1 instanceof Todo || task1 instanceof Event) {
            return -1;
        }

        if (task2 instanceof Todo || task2 instanceof Event) {
            return -1;
        }

        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            Deadline deadline1 = (Deadline) task1;
            Deadline deadline2 = (Deadline) task2;

            return deadline1.compareTo(deadline2);
        }

        return 0;
    }
}
