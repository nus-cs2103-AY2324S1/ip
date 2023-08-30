package bruno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    void testGetString() {
        Task task = new ToDo("ip tasks");
        assertEquals("[T][ ] ip tasks", task.getString());
    }

    @Test
    void testGetString_markedTask_stringGenerated() {
        Task task = new ToDo("assignment");
        task.markAsDone();
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
        task.markAsDone();
        assertEquals("T|✅|laundry", task.getFileString());
    }
}
