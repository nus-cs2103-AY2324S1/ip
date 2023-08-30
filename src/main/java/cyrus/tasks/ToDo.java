package cyrus.tasks;

import com.google.gson.annotations.SerializedName;

/**
 * To-Do task that only includes the name of the task to complete.
 */
public class ToDo extends Task {
    @SerializedName("type")
    private static final String TYPE = "todo";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
