package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.ToDo;

public class ToDoTest {

    /**
     * Checks if toString method returns string with correct format.
     */
    @Test
    public void toString_correctFormat() {
        ToDo toDo = new ToDo("abc");
        assertEquals("[T][ ] abc", toDo.toString());
    }
}
