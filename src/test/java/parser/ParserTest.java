package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {

    @Test
    public void convertTaskInput_deadlineCommand_success() throws DukeException {
        assertEqualsConvertTaskInputForDeadline("deadline Do notes /by 2/2/2019 2222");
    }

    @Test
    public void convertTaskInput_eventCommand_success() throws DukeException {
        assertEqualsConvertTaskInputForEvent("event zoom meeting /from 2/2/2222 2222 /to 2/2/2222 2322");
    }

    @Test
    public void convertTaskInput_todoCommand_success() throws DukeException {
        assertEqualsConvertTaskInputForTodo("todo Do notes");
    }

    @Test
    public void convertTaskInput_invalidDeadlineCommandAttributesName_throwDukeException() {
        try {
            String input = "deadline /by 2/2/2019 2222";
            new Parser("D").convertTaskInput(input);
            fail();
        } catch (DukeException e) {
            assertEquals("Your Deadline name is blank", e.getMessage());
        }
    }

    @Test
    public void convertTaskInput_invalidDeadlineCommandAttributesDate_throwDukeException() {
        try {
            String input = "deadline something /by 2/2/2019 a 2222";
            new Parser("D").convertTaskInput(input);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid format for date time, its dd/mm/yyyy tttt", e.getMessage());
        }
    }

    @Test
    public void convertTaskInput_invalidEventCommandAttributesDate_throwDukeException() {
        try {
            String input = "event sdfsdf /from 2/2/2019 2222 /to ";
            new Parser("E").convertTaskInput(input);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid format for /to date time, its dd/mm/yyyy tttt", e.getMessage());
        }
    }

    @Test
    public void convertTaskInput_invalidEventCommandAttributesName_throwDukeException() {
        try {
            String input = "event /from 2/2/2019 2222 /to 2/2/2020 2222";
            new Parser("E").convertTaskInput(input);
            fail();
        } catch (DukeException e) {
            assertEquals("Your Event name is blank", e.getMessage());
        }
    }

    @Test
    public void getCommand_validCommandTodo_success() {
        try {
            new Parser().getCommand("todo work");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validCommandDl_success() {
        try {
            new Parser().getCommand("deadline work /by 2/2/2222 2222");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validCommandEvent_success() {
        try {
            new Parser().getCommand("event meeting /from 2/2/2222 2222 /to 2/2/2222 2222");
        } catch (DukeException e) {
            fail();
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
    public void getCommand_invalidCommandDlFormat_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("deadline aa /by"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid deadline format", e.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandEventFormat_throwDukeException() {
        try {
            assertEquals(" ", new Parser().getCommand("event asd /to /from 22/2/2222 2222"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid event format", e.getMessage());
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
    public void assertEqualsConvertTaskInputForDeadline(String input) throws DukeException {
        ArrayList<String> texts = new Parser("D").convertTaskInput(input);
        assertEquals("Do notes", texts.get(0));
        assertEquals("2/2/2019 2222", texts.get(1));
    }

    // zoom meeting /from 2/2/2222 2222 /to 2/2/2222 2322
    public void assertEqualsConvertTaskInputForEvent(String input) throws DukeException {
        ArrayList<String> texts = new Parser("E").convertTaskInput(input);
        assertEquals("zoom meeting", texts.get(0));
        assertEquals("2/2/2222 2222", texts.get(1));
        assertEquals("2/2/2222 2322", texts.get(2));
    }

    // todo do notes
    public void assertEqualsConvertTaskInputForTodo(String input) throws DukeException {
        ArrayList<String> texts = new Parser("T").convertTaskInput(input);
        assertEquals("Do notes", texts.get(0));
    }



}
