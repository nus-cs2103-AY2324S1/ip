package TaskTypes;
import URBOI_PACKIN.TaskTypes.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    private Todo todo;

    @BeforeEach
    public void setUp() {
        // Initialize a Todo object before each test
        todo = new Todo("Sample Todo");
    }

    @Test
    public void testTodoConstructor() {
        // Check if the Todo constructor sets the description correctly
        assertEquals("Sample Todo", todo.getDescription());
    }

    @Test
    public void testTodoToFileString() {
        // Check the toFileString method for a Todo task
        assertEquals("T | 0 | Sample Todo", todo.toFileString());
    }

    @Test
    public void testTodoToString() {
        // Check the toString method for a Todo task
        assertEquals("[T][ ] Sample Todo", todo.toString());
    }
}
