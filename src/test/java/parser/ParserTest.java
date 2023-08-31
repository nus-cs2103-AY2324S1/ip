package parser;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void convertTaskInput_deadlineCommand_success() throws DukeException {
        assertEqualsConvertTaskInput("deadline Do notes /by 2/2/2019 2222");
    }

    @Test
    public void convertTaskInput_missingNameDeadlineCommand_success() {
        try {
            String input = "deadline /by 2/2/2019 2222";
            new Parser("D").convertTaskInput(input);
            fail();
        } catch (DukeException e) {
            assertEquals("Your Deadline name is blank", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandNotEmpty_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("sdfsfsdf"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandTodo_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("todooo"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandDl_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("deadlinesad"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandEvent_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("eventsdf"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandEmpty_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand(""));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

/*
Helper methods
 */
    // deadline eat /by 2/2/2019 2222
    public void assertEqualsConvertTaskInput(String input) throws DukeException {
        ArrayList<String> texts = new Parser("D").convertTaskInput(input);
        assertEquals("Do notes", texts.get(0));
        assertEquals("2/2/2019 2222", texts.get(1));
    }

}
