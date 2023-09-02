package ren.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ren.task.ToDo.class, name = "Todo"),
        @JsonSubTypes.Type(value = ren.task.Deadline.class, name = "ren.task.Deadline"),
        @JsonSubTypes.Type(value = Event.class, name = "ren.task.Event")
})

/**
 * Represents a task
 */
public class Task {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Toggles task from undone to done and vice versa
     */
    public boolean toggleTask() {
        this.isDone = !this.isDone;
        return this.isDone;
    }

    public boolean queryInDescription(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : "", this.description);
    }
}
