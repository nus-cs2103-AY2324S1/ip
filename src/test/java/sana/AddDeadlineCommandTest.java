package sana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class AddDeadlineCommandTest {

    @Test
    public void execute_emptyDeadlineDescription_sanaExceptionThrown() {
        Command c = new AddDeadlineCommand("deadline", " ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! Incomplete task description.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyDeadlineByDate_sanaExceptionThrown() {
        Command c = new AddDeadlineCommand("deadline", "description /by ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The deadline cannot be empty.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'", e.getMessage());
        }
    }
}
