package duke;

import org.junit.jupiter.api.Test;

import duke.task.TodoTask;

public class TodoTaskTest {
    @Test
    public void testToString() {
        TodoTask todoTask = new TodoTask("read book");
        assert todoTask.toString().equals("[T][ ] read book");
    }

    @Test
    public void testMarkTaskAsDone() {
        TodoTask todoTask = new TodoTask("read book");
        todoTask.markAsDone();
        assert todoTask.toString().equals("[T][X] read book");
    }
}
