package data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ToDoTaskTest {
    @Test
    public void testStringConversion() {
        ToDoTask testTask = new ToDoTask("test");
        assertEquals("[T][ ] test", testTask.toString());
        testTask.setDone();
        assertEquals("[T][X] test", testTask.toString());
    }

    @Test
    public void getType_emptyInput_correctString() {
        assertEquals("Todo", new ToDoTask("test").getType());
    }

    @Test
    public void getDateTime_emptyInput_correctString() {
        assertEquals("", new ToDoTask("test").getDateTime());
    }
}
