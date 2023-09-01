
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ToDo.class, name = "ToDo"),
        @JsonSubTypes.Type(value = Event.class, name = "Event"),
        @JsonSubTypes.Type(value = Deadline.class, name = "Deadline")
})
public class Task {
    public boolean done = false;
    public String task;
    public Task(String task) {
        this.task = task;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + ((done)?"X":" ") + "] " + task;
    }
}
