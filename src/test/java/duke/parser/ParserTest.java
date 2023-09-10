package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;


public class ParserTest {
    @Test
    public void parseToDoSuccess() {
        Parser parseLine = new Parser("todo boo");
        String actual = "";
        try {
            actual = parseLine.parseToDoArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expected = "boo";
        assertEquals(expected, actual);
    }

    @Test
    public void parseDeadlineSuccess() {
        Parser parseLine = new Parser("deadline boo /by 2001-12-10 11:11");
        String[] actual = {};
        try {
            actual = parseLine.parseDeadlineArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedName = "boo";
        String expectedBy = "2001-12-10 11:11";
        assertEquals(expectedName, actual[0]);
        assertEquals(expectedBy, actual[1]);
    }


    @Test
    public void testParseDeadlineError() {
        Parser parseLine = new Parser("deadline boo");
        Exception exception = assertThrows(DukeException.class, () -> {
            parseLine.parseDeadlineArguments();
        });
        String expected = "Hey, the Deadline given is Invalid! "
                + "Make sure that you follow this format:\n" + " 'taskDescription /by yyyy-mm-dd HH:mm'";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void parseEventSuccess() {
        Parser parseLine = new Parser("event boo /from 2001-12-10 11:11 /to 2001-12-10 11:12");
        String[] actual = {};
        try {
            actual = parseLine.parseEventArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedName = "boo";
        String expectedFrom = "2001-12-10 11:11";
        String expectedTo = "2001-12-10 11:12";
        assertEquals(expectedName, actual[0]);
        assertEquals(expectedFrom, actual[1]);
        assertEquals(expectedTo, actual[2]);
    }

    @Test
    public void testParseEventError() {
        Parser parseLine = new Parser("event boo");
        Exception exception = assertThrows(DukeException.class, () -> {
            parseLine.parseEventArguments();
        });
        String expected = "Hey, the Event given is Invalid!"
                + " Make sure that you follow this format:\n"
                + " 'eventDescription /from yyyy-mm-dd HH:mm /to yyyy-mm-dd HH:mm'";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void parseEditToDoSuccess() {
        Parser parseLine = new Parser("edit 5 /desc boo");
        String[] actual = {};
        try {
            actual = parseLine.parseEditArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedIndex = "5";
        String expectedDesc = "boo";
        assertEquals(expectedIndex, actual[0]);
        assertEquals(expectedDesc, actual[2]);
    }

    @Test
    public void parseEditDeadlineDescSuccess() {
        Parser parseLine = new Parser("edit 5 /desc boo");
        String[] actual = {};
        try {
            actual = parseLine.parseEditArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedIndex = "5";
        String expectedDesc = "boo";
        assertEquals(expectedIndex, actual[0]);
        assertEquals(expectedDesc, actual[2]);
    }

    @Test
    public void parseEditDeadlineBySuccess() {
        Parser parseLine = new Parser("edit 5 /by 2001-12-10 11:11");
        String[] actual = {};
        try {
            actual = parseLine.parseEditArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedIndex = "5";
        String expectedBy = "2001-12-10 11:11";
        assertEquals(expectedIndex, actual[0]);
        assertEquals(expectedBy, actual[2]);
    }

    @Test
    public void parseEditEventFromSuccess() {
        Parser parseLine = new Parser("edit 5 /from 2001-12-10 11:11");
        String[] actual = {};
        try {
            actual = parseLine.parseEditArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedIndex = "5";
        String expectedFrom = "2001-12-10 11:11";
        assertEquals(expectedIndex, actual[0]);
        assertEquals(expectedFrom, actual[2]);
    }

    @Test
    public void parseEditEventToSuccess() {
        Parser parseLine = new Parser("edit 5 /to 2001-12-10 11:11");
        String[] actual = {};
        try {
            actual = parseLine.parseEditArguments();
        } catch (DukeException e) {
            fail("parse failure");
        }

        String expectedIndex = "5";
        String expectedTo = "2001-12-10 11:11";
        assertEquals(expectedIndex, actual[0]);
        assertEquals(expectedTo, actual[2]);
    }

}
