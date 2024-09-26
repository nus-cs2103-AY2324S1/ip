package duke;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

/**
 * Test class for the Todo class.
 */
public class TodoTest {

    /**
     * Test the toString method for a not done task.
     */
    @Test
    public void testToString() {
        Todo task1 = new Todo("eat potato");
        assert task1.toString().equals("[T][ ] eat potato");
    }

    /**
     * Test marking a task as done.
     */
    @Test
    public void testMarkAsDone() {
        Todo task1 = new Todo("eat potato");
        task1.markAsDone();
        assert task1.toString().equals("[T][X] eat potato");
    }

    /**
     * Test marking a task as not done after it's marked as done.
     */
    @Test
    public void testMarkAsNotDone() {
        Todo task1 = new Todo("eat potato");
        task1.markAsDone();
        assert task1.toString().equals("[T][X] eat potato");
        task1.markAsNotDone();
        assert task1.toString().equals("[T][ ] eat potato");
    }
}
