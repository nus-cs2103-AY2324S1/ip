import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Complete iP", new ToDo("Complete iP").toString());
    }
}