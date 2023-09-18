package tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * The Task class represents a task. it has a description, and a checkbox to indicate if it is
 * complete or not.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Todo.class, name = "tasks.Todo"),
    @JsonSubTypes.Type(value = Deadline.class, name = "tasks.Deadline"),
    @JsonSubTypes.Type(value = Event.class, name = "tasks.Event")}
)

public class Task {

    private String description;
    private Boolean isDone;


    /**
     * A class representing a Task
     * @param desc description of the task
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;

    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    public String getDoneIcon() {
        return this.isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

}
