import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ToDo")
public class ToDo extends Task{
    public ToDo(@JsonProperty("task") String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
