package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation_description_success() {
        Todo todo = new Todo("Test Task");
        assertNotNull(todo);
        assertEquals("Test Task", todo.getDescription());
        assertFalse(todo.isDone);
    }

    @Test
    public void testTodoToString_notDone_success() {
        Todo todo = new Todo("Test Task");
        String expectedOutput = "[T][ ] Test Task";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void testMarkAsDone_success() {
        Todo todo = new Todo("Test Task");
        todo.markAsDone();
        assertTrue(todo.isDone);
        assertEquals("[T][X] Test Task", todo.toString());
    }

    @Test
    public void testTodoToFile_notDone_success() {
        Todo todo = new Todo("Test Task");
        String expectedOutput = "T | 0 | Test Task";
        assertEquals(expectedOutput, todo.toFile());
    }

    @Test
    public void testMarkAsUndone_afterDone_success() {
        Todo todo = new Todo("Test Task");
        todo.markAsDone();
        assertTrue(todo.isDone);
        todo.markAsUndone();
        assertFalse(todo.isDone);
        assertEquals("[T][ ] Test Task", todo.toString());
    }
}
