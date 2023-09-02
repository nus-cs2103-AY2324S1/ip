package tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Todo.class, name = "tasks.Todo"),
    @JsonSubTypes.Type(value = Deadline.class, name = "tasks.Deadline"),
    @JsonSubTypes.Type(value = Event.class, name = "tasks.Event"),}
)

public class Task {

  private String description;
  private Boolean done;

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
