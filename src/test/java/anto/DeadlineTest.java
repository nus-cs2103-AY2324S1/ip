package anto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadlineTaskTest() {
        try {
            Deadline newTodoTask = new Deadline("Read book", "01-09-2023 23:59");
            assertEquals("[D] [ ] Read book (by: 01 Sep 2023 23:59)", newTodoTask.toString());
        } catch (AntoException e) {
            fail("Expected exception was thrown");
        }
    }

    @Test
    public void markAndUnmarkTodoTaskTest() {
        try {
            Deadline newTodoTask = new Deadline("Read book", "01-09-2023 23:59");
            newTodoTask.markAsDone();
            assertEquals("X", newTodoTask.getStatusIcon());
            assertEquals("[D] [X] Read book (by: 01 Sep 2023 23:59)", newTodoTask.toString());
            newTodoTask.unmark();
            assertEquals(" ", newTodoTask.getStatusIcon());
            assertEquals("[D] [ ] Read book (by: 01 Sep 2023 23:59)", newTodoTask.toString());
        } catch (AntoException e) {
            fail("Expected exception was thrown");
        }
    }
}
