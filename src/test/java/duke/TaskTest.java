package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests task class.
 */
public class TaskTest {

    /**
     * Tests markAsDone function.
     */
    @Test
    public void markAsDone() {
        Task t = new Task("test", false);
        t.markAsDone();
        assertEquals(true, t.isDone);
    }
}
