package task;

import Duke.exception.EmptyTaskDescException;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TestTodoConstructor() throws EmptyTaskDescException {
        assertEquals(new Todo("a").toString(), "[T][ ] a");
    }

    @Test
    public void TestTodoToSaveFormat() throws EmptyTaskDescException {
        assertEquals(new Todo("a").toSaveFormat(), "todo:a|false");
    }
}