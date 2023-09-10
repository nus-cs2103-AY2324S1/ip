package jerma.tasks; //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private final Task todo = new Todo("desc");

    @Test
    public void todo_constructor_success() {
        assertEquals("[T][ ] desc", todo.toString());
    }

    @Test
    public void todo_save_success() {
        assertEquals("T|0|desc", todo.save());
    }

    @Test
    public void todo_setDone_success() {
        todo.setDone();
        assertEquals("[T][X] desc", todo.toString());
    }

    @Test
    public void todo_setUndone_success() {
        todo.setUndone();
        assertEquals("[T][ ] desc", todo.toString());
    }
}