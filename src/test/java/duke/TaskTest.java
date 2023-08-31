package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testFileStringConversion() {
        Task task = new Task("Test");
        task.markAsDone();
        assertEquals("  | 1 | Test", task.toFileString());
    }
}
