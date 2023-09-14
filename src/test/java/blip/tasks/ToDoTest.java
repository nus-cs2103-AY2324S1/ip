package blip.tasks;

import org.junit.jupiter.api.Test;

import blip.priority.Priority;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {
    @Test
    public void saveToFileStringTest_markAndUnmark_success() {
        ToDo todo = new ToDo("Task A", false, Priority.HIGH);
        assertEquals("T | 0 | HIGH | Task A", todo.saveToFileString());
        todo.markStatus();
        assertEquals("T | 1 | HIGH | Task A", todo.saveToFileString());
        todo.unmarkStatus();
        assertEquals("T | 0 | HIGH | Task A", todo.saveToFileString());
    }
    @Test
    public void toStringTest_markAndUnmark_success() {
        ToDo todo = new ToDo("Task A", false, Priority.MEDIUM);
        assertEquals("[T][ ][MEDIUM] Task A", todo.toString());
        todo.markStatus();
        assertEquals("[T][X][MEDIUM] Task A", todo.toString());
        todo.unmarkStatus();
        assertEquals("[T][ ][MEDIUM] Task A", todo.toString());
        todo.setPriority(Priority.HIGH);
        assertEquals("[T][ ][HIGH] Task A", todo.toString());
    }
}
