package ruiz;

import org.junit.jupiter.api.Test;
import ruiz.command.Command;
import ruiz.exception.BotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getCommand_success_todo() {
        assertEquals(Command.TODO, new Parser().getCommand("todo borrow book"));
    }

    @Test
    public void getCommand_success_unknown() {
        assertEquals(Command.UNKNOWN, new Parser().getCommand("vbeirvernovner"));
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