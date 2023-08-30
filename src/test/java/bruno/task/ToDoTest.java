package bruno.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    void testGetString() {
        Task task = new ToDo("ip tasks");
        assertEquals("[T][ ] ip tasks", task.getString());
    }

    @Test
    void testGetString_markedTask_stringGenerated() {
        Task task = new ToDo("assignment");
        task.isDone = true;
        assertEquals("[T][X] assignment", task.getString());
    }

    @Test
    void testGetFileString() {
        Task task = new ToDo("laundry");
        assertEquals("T|⭕️|laundry", task.getFileString());
    }

    @Test
    void testGetFileString_markedTask_stringGenerated() {
        Task task = new ToDo("laundry");
        task.isDone = true;
        assertEquals("T|✅|laundry", task.getFileString());
    }
}
