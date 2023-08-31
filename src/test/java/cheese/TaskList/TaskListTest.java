package cheese.TaskList;

import cheese.Task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
  @Test
  public void testAddTask() {
    TaskList taskList = new TaskList();
    Task task = new Task('T',"Buy Milk");
    taskList.addTask(task);
    assertEquals(taskList.getTask(0), task);
  }
}
