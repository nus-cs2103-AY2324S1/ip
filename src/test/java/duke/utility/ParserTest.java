package duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {

    @Test
    public void testValidateToDoCommand() {
        try {
            String result = Parser.validateToDoCommand("todo Buy groceries");
            assertEquals(" Buy groceries", result);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown.");
        }

        assertThrows(DukeException.class, () -> {
            Parser.validateToDoCommand("todo");
        });
    }

    @Test
    public void testValidateDeadlineCommand() {
        try {
            String result = Parser.validateDeadlineCommand("deadline Submit report /by 2023-09-01");
            assertEquals(" Submit report /by 2023-09-01", result);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown.");
        }

        assertThrows(DukeException.class, () -> {
            Parser.validateDeadlineCommand("deadline");
        });

        assertThrows(DukeException.class, () -> {
            Parser.validateDeadlineCommand("deadline Submit report /by");
        });

        assertThrows(DukeException.class, () -> {
            Parser.validateDeadlineCommand("deadline Submit report /by2023-09-01");
        });
    }

    @Test
    public void testValidateEventCommand() {
        try {
            String result = Parser.validateEventCommand("event Birthday party /from 2023-09-01 /to 2023-09-02");
            assertEquals(" Birthday party /from 2023-09-01 /to 2023-09-02", result);
        } catch (DukeException e) {
            fail("Unexpected DukeException thrown.");
        }

        assertThrows(DukeException.class, () -> {
            Parser.validateEventCommand("event");
        });

        assertThrows(DukeException.class, () -> {
            Parser.validateEventCommand("event Birthday party /from");
        });

        assertThrows(DukeException.class, () -> {
            Parser.validateEventCommand("event Birthday party /from 2023-09-01 /to");
        });

        assertThrows(DukeException.class, () -> {
            Parser.validateEventCommand("event Birthday party /from 2023-09-01 /to 2023-09-02 /from");
        });
    }

    @Test
    public void testParseInt() {
        assertEquals(42, Parser.parseInt("42"));

        assertEquals(-1, Parser.parseInt("not_a_number"));
    }
}

