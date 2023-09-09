package nexus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodo() {
        Todo todo = new Todo("2103 tut");
        todo.setDone();
        assertEquals("[T][X] 2103 tut", todo.toString());
    }

    @Test
    public void testTodoStorage() {
        Todo todo = new Todo("2103 tut");
        todo.setDone();
        assertEquals("T|1|2103 tut", todo.toStorageString());
    }
}
