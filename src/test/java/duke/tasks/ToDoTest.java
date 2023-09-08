package duke.tasks;

import duke.tasks.Task;
import duke.exceptions.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ToDoTest {

    @Test
    public void Todo_created_withemptystring_exceptionThrown() {
        try {
            assertEquals("[T][ ] ", new ToDo(""));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.toString());
        }
    }

    @Test
    public void Todo_created_withhello_success() {
        try {
            assertEquals("[T][ ] hello", new ToDo("hello").toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void datastring_helloinput_success() {
        try {
            assertEquals("T : 0 : hello", new ToDo("hello").dataString());
        } catch (Exception e) {
            fail();
        }
    }

}
