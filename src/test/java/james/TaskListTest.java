package james;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        String[] stubs = {"stub1", "stub2", "stub3"};
        TaskList taskList = new TaskList();
        for (String stub : stubs) {
            taskList.addTask(new ToDoTask(stub));
        }
        assertEquals(taskList.size(), stubs.length);
    }

    @Test
    public void testTaskString() {
        String[] stubs = {"stub1", "stub2", "stub3"};
        TaskList taskList = new TaskList();
        for (String stub : stubs) {
            taskList.addTask(new ToDoTask(stub));
        }
        for (int i = 0; i < stubs.length; i++) {
            assertEquals(taskList.getTask(i).toString(), "[T][ ] " + stubs[i]);
        }
    }
}
