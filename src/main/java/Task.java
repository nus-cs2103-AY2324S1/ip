
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
    @JsonSubTypes.Type(value = Deadline.class, name = "Deadline"),
    @JsonSubTypes.Type(value = Event.class, name = "Event"),}
)

public class Task {

  private String description;
  private boolean done;

  public Task(String desc) {
    this.description = desc;
    this.done = false;

  }

  public void setDone() {
    this.done = true;
  }

  public void setUnDone() {
    this.done = false;
  }

  public String getDoneIcon() {
    return this.done ? "X" : " ";
  }

  public String getDescription() {
    return this.description;
  }

}
