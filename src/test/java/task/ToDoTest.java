package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo test = new ToDo("todo go gym");
        assertEquals("[T] [ ] go gym", test.toString());
    }

    @Test
    public void toFileString() {
        ToDo test = new ToDo("todo go gym");
        assertEquals("T | 0 | go gym", test.toFileString());
    }
}
