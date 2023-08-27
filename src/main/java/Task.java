import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ToDo.class, name = "Todo"),
        @JsonSubTypes.Type(value = Deadline.class, name = "Deadline"),
        @JsonSubTypes.Type(value = Event.class, name = "Event")
})

public class Task {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected final String description;
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
    public void toggleTask() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : "", this.description);
    }
}
