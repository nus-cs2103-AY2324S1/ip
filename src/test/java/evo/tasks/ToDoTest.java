package evo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit test cases for the `To Do` class.
 * It tests the functionality of the `outputMsg` and `toString` methods of the `To Do` class, which are responsible for
 * generating formatted output messages and string representations of tasks.
 * The `To Do` class represents a simple task with a description and a completion status.
 *
 * @author NgChunMan
 */
public class ToDoTest {

    /**
     * Test case for the `outputMsg` method of the `To Do` class.
     * It checks if the method correctly formats the output message for a To Do task and updates the completion status.
     */
    @Test
    public void outputMsg_markAndUnmarkTask_success() {
        ToDo toDo = new ToDo("read book");
        assertEquals("T | 0 | read book", toDo.outputMsg());
        toDo.markAsDone();
        assertEquals("T | 1 | read book", toDo.outputMsg());
        toDo.markAsNotDone();
        assertEquals("T | 0 | read book", toDo.outputMsg());
    }

    /**
     * Test case for the `toString` method of the `To Do` class.
     * It checks if the method correctly formats the string representation of a To Do task with the completion status
     * indicator.
     */
    @Test
    public void toString_markAndUnmarkTask_success() {
        ToDo toDo = new ToDo("write novel");
        assertEquals("[T][ ] write novel", toDo.toString());
        toDo.markAsDone();
        assertEquals("[T][X] write novel", toDo.toString());
        toDo.markAsNotDone();
        assertEquals("[T][ ] write novel", toDo.toString());
    }
}
