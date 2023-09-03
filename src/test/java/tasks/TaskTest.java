import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringTest() {
        Task test = new Task("test123");
        assertEquals("[ ] test123", test.toString());
    }

    @Test
    public void markDoneTest() {
        Task test = new Task("test123");
        test.markDone();
        assertEquals("[X] test123", test.toString());
    }
}