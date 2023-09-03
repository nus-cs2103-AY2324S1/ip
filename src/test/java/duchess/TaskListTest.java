package duchess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void indexOfTest() {
        TaskList taskList = new TaskList();

        ToDo todo = new ToDo("Hello World!");
        taskList.addTask(todo);

        assertEquals(0, taskList.indexOf(todo));

        taskList.removeTask(0);

        assertEquals(-1, taskList.indexOf(todo));
    }
}
