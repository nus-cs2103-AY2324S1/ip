package Frenchie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class TaskTest {
    @Test
    public void markTaskCompleteTest() {
        ToDo testTask = new ToDo("Test Task");
        testTask.mark_as_completed();
        assertTrue(testTask.check_Completion());
    }
    /**@Test
    public void markTaskIncompleteTest() {
        TaskList tasklist = new TaskList();
        ToDo testTask = new ToDo("Test Task");
        tasklist.addTask(testTask);
        assertTrue(ToDo.check_Completion());
    }**/
}
