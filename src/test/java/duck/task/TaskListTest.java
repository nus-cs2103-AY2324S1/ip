package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duck.exceptions.DuckException;

public class TaskListTest {

    @Test
    public void testSetToDo() throws DuckException {
        String input = "Do ip";
        TaskList test = new TaskList();
        ToDo todo = test.setToDo(input);
        assertEquals("Do ip", todo.getDescription());
    }
    @Test
    public void testEmptySpaces() {
        String input = "   ";
        TaskList test = new TaskList();
        assertThrows(DuckException.class, ()->test.setToDo(input));
    }

    @Test
    public void testSpacesAndWords() throws DuckException {
        String input = " project ";
        TaskList test = new TaskList();
        ToDo todo = test.setToDo(input);
        assertEquals("project", todo.getDescription());
    }

}
