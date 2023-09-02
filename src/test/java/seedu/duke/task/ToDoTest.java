package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void testDescription() {
        ToDo task = new ToDo("test1");
        assertEquals(task.getDescription(), "test1");
    }

    @Test
    void testString() {
        ToDo task = new ToDo("test2");
        assertEquals(task.toString(), "[T][ ] test2");
    }
}