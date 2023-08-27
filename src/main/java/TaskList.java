import java.util.ArrayList;

import tasks.Task;

public class TaskList extends ArrayList<Task> {
  @Override
  public String toString() {
    String strRep = "";
    for (int i = 0; i < this.size(); i++) {
      strRep += String.format("%d. %s", i + 1, this.get(i));
    }
    return strRep;
  }
}
