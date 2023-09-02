package tasks;

import com.fasterxml.jackson.annotation.JsonTypeName;
import tasks.Task;

@JsonTypeName("tasks.Todo")
public class Todo extends Task {


  public static final String taskType = "T";

  public Todo() {
    super("");
  }

  public Todo(String desc) {
    super(desc);
  }

}
