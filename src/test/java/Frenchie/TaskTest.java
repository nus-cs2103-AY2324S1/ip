package Frenchie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class TaskTest {
    @Test
    public void markTaskCompleteTest() {
        ToDo testTask = new ToDo("Test Task");
        testTask.setIsCompleted();
        assertTrue(testTask.check_Completion());
    }
}
