package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    public void testAddTask() {
        taskList.addTask(new Task("Test"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testRemoveTask() {
        taskList.addTask(new Task("Test"));
        taskList.removeTask(0);
        assertEquals(0, taskList.getSize());
    }
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}
