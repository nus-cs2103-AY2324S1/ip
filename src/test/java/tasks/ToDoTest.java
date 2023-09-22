package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ToDoTest {

    @Test
    public void testConstructorWithDescription() {
        ToDo todo = new ToDo("Eat potato");
        assertEquals("Eat potato", todo.getDescription());
        assertFalse(todo.isDone);
    }

    @Test
    public void testConstructorWithDescriptionAndIsDone() {
        ToDo todo1 = new ToDo("Eat potati", true);
        assertEquals("Eat potati", todo1.getDescription());
        assertTrue(todo1.isDone);
    }

    @Test
    public void testToTextMethod() {
        ToDo todo = new ToDo("Watch paint dry");
        assertEquals("T false Watch paint dry", todo.toText());
    }

    @Test
    public void testToStringMethod() {
        ToDo todo = new ToDo("Sleep", true);
        assertEquals("[T] [X] Sleep", todo.toString());
    }

}
