package duke.task;

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
    public boolean done = false;
    public String task;

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
        done = true;
    }

    /**
     * Sets boolean done to false;
     */
    public void unmark() {
        done = false;
    }

    @Override
    public String toString() {
        return "[" + ((done)?"X":" ") + "] " + task;
    }
}
