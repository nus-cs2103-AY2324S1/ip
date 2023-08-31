package anto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void createDeadlineTaskTest() {
        try {
            Event newTodoTask = new Event("Book meeting", "02-09-2023 20:00", "02-09-2023 21:00");
            assertEquals("[E] [ ] Book meeting (from: 02 Sep 2023 20:00 to: 02 Sep 2023 21:00)",
                    newTodoTask.toString());
        } catch (AntoException e) {
            fail("Expected exception was thrown");
        }
    }

    @Test
    public void markAndUnmarkTodoTaskTest() {
        try {
            Event newTodoTask = new Event("Book meeting", "02-09-2023 20:00", "02-09-2023 21:00");
            newTodoTask.markAsDone();
            assertEquals("X", newTodoTask.getStatusIcon());
            assertEquals("[E] [X] Book meeting (from: 02 Sep 2023 20:00 to: 02 Sep 2023 21:00)",
                    newTodoTask.toString());
            newTodoTask.unmark();
            assertEquals(" ", newTodoTask.getStatusIcon());
            assertEquals("[E] [ ] Book meeting (from: 02 Sep 2023 20:00 to: 02 Sep 2023 21:00)",
                    newTodoTask.toString());
        } catch (AntoException e) {
            fail("Expected exception was thrown");
        }
    }
}