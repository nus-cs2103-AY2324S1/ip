package cyrus.tasks;

import com.google.gson.annotations.SerializedName;

/**
 * Represents general task stored within the {@code TaskList}.
 */
public class Task {
    public final String name;
    @SerializedName("status")
    private boolean done;

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
        this.done = done;
    }

    /**
     * Custom string representation of {@code Task}.
     *
     * @return string representation of {@code Task}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
