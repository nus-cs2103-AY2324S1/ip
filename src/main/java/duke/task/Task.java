package duke.task;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** Task class that contains a task string */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ToDo.class, name = "ToDo"),
    @JsonSubTypes.Type(value = Event.class, name = "Event"),
    @JsonSubTypes.Type(value = Deadline.class, name = "Deadline")
})
public class Task {
    private boolean isDone = false;
    private final String task;
    private String[] tags = {};

    /**
     * Initialize Task.
     *
     * @param task Task.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Sets boolean done to true.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Sets boolean done to false.
     */
    public void unmark() {
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    /**
     * Add tag to task.
     *
     * @param tag Tag.
     */
    public void addTag(String tag) {
        boolean found = false;
        for (String tempTag : tags) {
            if (Objects.equals(tempTag, tag)) {
                found = true;
                break;
            }
        }

        if (!found) {
            tags = Arrays.copyOf(tags, tags.length + 1);
            tags[tags.length - 1] = tag;
        }
    }

    public String[] getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "[" + ((isDone) ? "X" : " ") + "] " + task;
    }
}
