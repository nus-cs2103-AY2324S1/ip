package duck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser.parse("foo bar baz");
            fail();
        } catch (Exception e) {
            assertEquals("Im sorry, I don't know what that means.", e.getMessage());
        }
    }

    @Test
    public void parse_markInvalidIndex_exceptionThrown() {
        try {
            Parser.parse("mark abc");
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid task number.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkInvalidIndex_exceptionThrown() {
        try {
            Parser.parse("unmark abc");
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid task number.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteInvalidIndex_exceptionThrown() {
        try {
            Parser.parse("delete abc");
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid task number.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineInvalidDateFormat_exceptionThrown() {
        // Invalid date format
        try {
            Parser.parse("deadline abc /by 01-09-2023");
            fail();
        } catch (Exception e) {
            assertEquals("Please follow the dd/mm/yyyy format.", e.getMessage());
        }
    }

    @Test
    public void parse_eventInvalidDateFormat_exceptionThrown() {
        // Invalid date format
        try {
            Parser.parse("event abc /from 01-09-2023 /to 01-10-2023");
            fail();
        } catch (Exception e) {
            assertEquals("Please follow the dd/mm/yyyy format.", e.getMessage());
        }
    }
}