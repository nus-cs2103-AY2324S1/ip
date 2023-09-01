package blip.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {
    @Test
    public void saveToFileStringTest_markAndUnmark_success() {
        ToDo todo = new ToDo("Task A", false);
        assertEquals("T | 0 | Task A", todo.saveToFileString());
        todo.markStatus();
        assertEquals("T | 1 | Task A", todo.saveToFileString());
        todo.unmarkStatus();
        assertEquals("T | 0 | Task A", todo.saveToFileString());
    }
    @Test
    public void toStringTest_markAndUnmark_success() {
        ToDo todo = new ToDo("Task A", false);
        assertEquals("[T][ ] Task A", todo.toString());
        todo.markStatus();
        assertEquals("[T][X] Task A", todo.toString());
        todo.unmarkStatus();
        assertEquals("[T][ ] Task A", todo.toString());
    }
}
