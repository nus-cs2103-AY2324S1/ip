package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testMarkDone() {
        Task testTask = new Task("testTask");
        testTask.markDone();
        assertTrue(testTask.getIsDone());
    }

    @Test
    public void testMarkUndone(){
        Task testTask = new Task("testTask");
        testTask.markDone();
        testTask.markUndone();
        assertFalse(testTask.getIsDone());
    }
}