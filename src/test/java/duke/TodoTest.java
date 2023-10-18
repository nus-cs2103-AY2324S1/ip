package duke;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void constructorTest() {
        Todo read = new Todo("read");
        assertEquals("T | 0 | read", read.toString());
    }

}
