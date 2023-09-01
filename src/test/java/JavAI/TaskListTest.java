package javai;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new Task("Sample Task");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }
    @Test
    void testDeleteTask() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        assertEquals(3, taskList.size());

        taskList.delete(1);

        assertEquals(2, taskList.size());
        assertThrows(JavAIException.class, () -> taskList.get(2));
    }

}
