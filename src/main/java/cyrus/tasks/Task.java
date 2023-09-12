package cyrus.tasks;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

/**
 * Represents general task stored within the {@code TaskList}.
 */
public class Task {
    public final String name;
    @SerializedName("status")
    private boolean isDone;
    @SerializedName("completed_date")
    private LocalDate completedDate;

    /**
     * Base constructor to set Task name.
     *
     * @param name name of Task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Set {@code done} value.
     *
     * @param done value to set to
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    /**
     * Returns the value of {@code isDone}.
     *
     * @return {@code isDone} value.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the date completed of task.
     *
     * @return {@code completedDate} value.
     */
    public LocalDate getCompletedDate() {
        return completedDate;
    }

    /**
     * Custom string representation of {@code Task}.
     *
     * @return string representation of {@code Task}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }
}
