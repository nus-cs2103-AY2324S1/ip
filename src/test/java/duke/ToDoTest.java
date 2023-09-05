package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

/**
 * Test cases for the Todo class.
 */
public class ToDoTest {

    /**
     * Tests the toString method of Todo when it's not done and when it's done.
     */
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Sample Todo");
        assertEquals("[T][ ] Sample Todo", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] Sample Todo", todo.toString());
    }
}
