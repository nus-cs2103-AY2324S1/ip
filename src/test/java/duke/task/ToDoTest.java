package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void task_todoString_correctlyParsed() {
        ToDo todo = new ToDo("Test Todo", false);
        Assertions.assertEquals("[T][ ] Test Todo", todo.toString());
    }

    @Test
    public void task_todoDataString_correctlyParsed() {
        ToDo todo = new ToDo("Test Todo", true);
        Assertions.assertEquals("T|1|Test Todo", todo.toDataString());
    }

    @Test
    public void task_markToDo_marked() {
        ToDo todo = new ToDo("Test Todo", false);
        todo.markDone();
        Assertions.assertEquals("[T][X] Test Todo", todo.toString());
    }
}
