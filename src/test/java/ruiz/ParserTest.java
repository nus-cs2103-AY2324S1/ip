package ruiz;

import org.junit.jupiter.api.Test;
import ruiz.command.Command;
import ruiz.exception.BotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getCommand_success() {
        assertEquals(Command.UNKNOWN, new Parser().getCommand("vbeirvernovner"));
        assertEquals(Command.TODO, new Parser().getCommand("todo eat"));
        assertEquals(Command.DEADLINE, new Parser().getCommand("deadline return books /by 2019-11-11 1900"));
        assertEquals(Command.EVENT, new Parser().getCommand(
                "event project meeting /from 2019-11-11 1200 /to 2019-11-11 1200"));
        assertEquals(Command.DELETE, new Parser().getCommand("delete 1"));
        assertEquals(Command.MARK, new Parser().getCommand("mark 1"));
        assertEquals(Command.UNMARK, new Parser().getCommand("unmark 1"));
        assertEquals(Command.LIST, new Parser().getCommand("list"));
        assertEquals(Command.BYE, new Parser().getCommand("bye"));
    }

    @Test
    public void getEventDescription_success() throws BotException {
        assertEquals("project meeting",
                new Parser().getEventDescription(
                        "event project meeting /from 2019-11-11 1200 /to 2019-11-11 1200"));
    }

    @Test
    public void getEventDescription_exceptionThrown() throws BotException {
        try {
            assertEquals("event project meeting",
                    new Parser().getEventDescription(
                            "event project meeting /from 2019-11-11 1200 "));
            fail();
        } catch (BotException e) {
            assertEquals("OOPS!!! The description the event is incomplete." +
                    "it should be in the format 'event *** /from *** /to ***'", e.getMessage());
        }
    }

}