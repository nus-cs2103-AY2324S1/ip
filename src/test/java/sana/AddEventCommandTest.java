package sana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class AddEventCommandTest {
    @Test
    public void execute_emptyEventDescription_sanaExceptionThrown() {
        Command c = new AddEventCommand("event", " ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! Incomplete command. Make sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyEventFromDate_sanaExceptionThrown() {
        Command c = new AddEventCommand("event", "/from ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The 'from' field cannot be empty.\nMake sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyEventToDate_sanaExceptionThrown() {
        Command c = new AddEventCommand("event", "description /from 2020-10-20 /to ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The 'to' field cannot be empty.\nMake sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }
}
