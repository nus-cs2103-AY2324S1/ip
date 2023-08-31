import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;


public class TaskList {

  TaskList() {
    tasks = new ArrayList<>();
  }

  @JsonIgnore
  public int size() {
    return tasks.size();
  }


  @JsonIgnore
  public boolean isEmpty() {
    return tasks.isEmpty();
  }


  @JsonIgnore
  public void add(Task task) {
    tasks.add(task);
  }

  @JsonIgnore
  public Task get(int index) {
    return tasks.get(index);
  }

  @JsonIgnore
  public void remove(int index) {
    tasks.remove(index);
  }


  private ArrayList<Task> tasks;

}
