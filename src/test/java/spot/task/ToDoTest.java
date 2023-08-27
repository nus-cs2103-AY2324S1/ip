package spot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] takotime", new ToDo("takotime").toString());
        assertEquals("[T][X] takotime", new ToDo("takotime", true).toString());
        assertEquals("[T][ ] takotime", new ToDo("takotime", false).toString());
    }
}
