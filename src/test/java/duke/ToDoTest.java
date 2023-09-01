package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test_markAsDone() {
        ToDo task = new ToDo("testing", false);
        task.markAsDone();
        assertEquals(true, task.isDone);
    }

    @Test
    public void test_toString() {
        ToDo task = new ToDo("testing", false);
        assertEquals("[T][ ] testing", task.toString());
    }
}
