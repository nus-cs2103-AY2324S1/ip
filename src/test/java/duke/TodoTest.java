package duke;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo(" have fun");
        assert todo.toString().equals("[T][ ] have fun");
    }
}
