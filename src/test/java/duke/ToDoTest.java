package duke;

import duke.exception.DukeNoDescriptionException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest() {
        ToDo todo;
        try {
            todo = new ToDo("");
        } catch (DukeNoDescriptionException e) {
            assertEquals(e.getMessage(),
                    "OOPS!!! The description of a "
                    + "todo"
                    + " cannot be empty."
                    + "\n");
        }
    }

    @Test
    public void anotherDummyTest() {
        ToDo todo;
        try {
            todo = new ToDo("todo    read     a    book");
        } catch (DukeNoDescriptionException e) {
            return;
        }
        assertEquals("[T][ ] read a book", todo.toString());
    }
}
