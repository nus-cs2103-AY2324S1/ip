package duke.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import task.Task;


public class ToDoListTest {

    @Test
    public void toMarkAsDone() {
        Task taskTest = new Task("updateIP");
        taskTest.isCompleted();
        assertTrue(taskTest.checkIsDone());
    }

    @Test
    public void toMarkAsUnDone() {
        Task taskTest = new Task("updateIP");
        taskTest.isCompleted();
        taskTest.isNotCompleted();
        assertFalse(taskTest.checkIsDone());
    }
}
