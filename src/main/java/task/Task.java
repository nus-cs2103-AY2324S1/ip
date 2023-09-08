package task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents an abstract task class.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
        @JsonSubTypes.Type(value = Deadline.class, name = "Deadline"),
        @JsonSubTypes.Type(value = Event.class, name = "Event")
})
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with a task name.
     *
     * @param description The name of the constructed task.
     */
    @JsonCreator
    public Task(@JsonProperty("description") String description) {
        this.description = description;
        assert this.description != null : "task description should not be null";
    }


    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;

        assert this.description != null : "task description should not be null";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Marks this task as done or not done.
     * @param done Whether the task is done or not
     */
    public void markTask(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        char marked = 'X';
        return "[" + (isDone ? marked : " ") + "] " + this.description;
    }

    /**
     * Finds whether the description has the pattern in it.
     *
     * @param pattern The pattern to be matched.
     * @return True if pattern is found in description.
     */
    public boolean descriptionContains(String pattern) {
        int pos = this.description.indexOf(pattern);
        return pos >= 0;
    }
}
