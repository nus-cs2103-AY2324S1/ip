import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Todo")
public class Todo extends Task {


  public static final String taskType = "T";

  Todo() {
    super("");
  }

  Todo(String desc) {
    super(desc);
  }

}
