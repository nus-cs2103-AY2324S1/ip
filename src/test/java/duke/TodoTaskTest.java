package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TodoTask;

public class TodoTaskTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] return book", new TodoTask(false, "return book").toString());
        assertEquals("[T][X] return book", new TodoTask(true, "return book").toString());
    }

    @Test
    public void testMarkAsDone() {
        TodoTask todoTask = new TodoTask(false, "return book");
        TodoTask todoTask2 = new TodoTask(true, "return book");
        todoTask.markAsDone();
        assertEquals(todoTask2.toString(), todoTask.toString());
    }
}
