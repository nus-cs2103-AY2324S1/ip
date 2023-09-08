package duke.tasks;

import duke.tasks.Task;
import duke.exceptions.DukeException;

import org.junit.jupiter.api.Test;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void Todo_created_withemptystring_exceptionThrown() {
        try {
            assertEquals(0, new Todo(""));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.toString());
        }
    }

}
