package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        Task t = new Todo("description", false);
        assertEquals(t.toString(), "  [T][ ] description");
    }
}
