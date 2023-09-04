package james;

import static org.junit.jupiter.api.Assertions.assertEquals;

import james.TaskList;
import james.ToDoTask;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        ToDoTask stub = new ToDoTask("stub");
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            taskList.addTask(stub);
        }
        assertEquals(taskList.size(), 10);
    }

    @Test
    public void testTaskString() {
        ToDoTask stub = new ToDoTask("stub");
        TaskList taskList = new TaskList();
        for (int i = 0; i < 5; i++) {
            taskList.addTask(stub);
        }
        String expected = "1.[T][ ] stub\n2.[T][ ] stub\n3.[T][ ] stub\n4.[T][ ] stub\n5.[T][ ] stub";
        assertEquals(taskList.toString(), expected);
    }
}
