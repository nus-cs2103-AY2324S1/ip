package helpbuddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import helpbuddy.exception.HelpBuddyException;

public class ToDoTest {

    @Test
    public void toDo_noTaskName_exceptionThrown() {
        try {
            new ToDo("");
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! The description of a todo cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void toDo_success() {
        try {
            ToDo task = new ToDo("read");
            assertEquals("T|0|read", task.stringifyTask());
        } catch (HelpBuddyException e) {
            fail();
        }
    }
}
