package chatbot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseAdd_invalidCommand_exceptionThrown() {
        try {
            Parser parser = new Parser();
            assertEquals("", parser.parseAdd("blah"));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Please type a valid command!", e.getMessage());
        }
    }

    @Test
    public void parseAdd_missingTodoDescription_exceptionThrown() {
        try {
            Parser parser = new Parser();
            assertEquals("", parser.parseAdd("add todo"));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! Please specify the description for this Todo!", e.getMessage());
        }
    }

    @Test
    public void parseAdd_missingDeadlineDate_exceptionThrown() {
        try {
            Parser parser = new Parser();
            assertEquals("", parser.parseAdd("add deadline read"));
            fail();
        } catch (Exception e) {
            assertEquals(
                    "OOPS!!! Please specify the description and deadline in the correct format for this Deadline!",
                    e.getMessage());
        }
    }

    @Test
    public void parseAdd_missingEventDate_exceptionThrown() {
        try {
            Parser parser = new Parser();
            assertEquals("", parser.parseAdd("add event shop /from 2023-09-09 /to "));
            fail();
        } catch (Exception e) {
            assertEquals(
                    "OOPS!!! Please specify the description, start, and end time in the correct format for this Event!",
                    e.getMessage());
        }
    }

    @Test
    public void parseAdd_validEventCommand_success() {
        try {
        Parser parser = new Parser();
        List<String> res = parser.parseAdd("add event shop /from 2023-09-09 /to 2023-10-09");
        assertEquals("event", res.get(0));
        assertEquals("shop", res.get(1));
        assertEquals("2023-09-09", res.get(2));
        assertEquals("2023-10-09", res.get(3));
        } catch (Exception e) {
            fail();
        }
    }
}
