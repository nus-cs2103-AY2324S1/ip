package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDone() {
        Task t = new Task("test", false);
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
