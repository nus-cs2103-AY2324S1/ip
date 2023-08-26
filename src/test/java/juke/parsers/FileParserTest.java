package juke.parsers;

import juke.exceptions.JukeParseException;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTodo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileParserTest {
    @Test
    public void parseTask_normal_success() {
        assertEquals(new JukeTodo("testing", false).toString(), FileParser.parseTask("T|F|testing").toString());
        assertEquals(new JukeDeadline("testing", DateTimeParser.fromParsedString("2027-07-15T15:34"), false).toString(),
                     FileParser.parseTask("D|F|testing|2027-07-15T15:34").toString());
        assertEquals(new JukeEvent("testing", DateTimeParser.fromParsedString("2027-07-15T15:34"),
                                   DateTimeParser.fromParsedString("2027-08-15T15:34"), false).toString(),
                     FileParser.parseTask("E|F|testing|2027-07-15T15:34|2027-08-15T15:34").toString());
    }

    @Test
    public void parseTask_gibberish_failure() {
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("this is obviously wrong"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("T|Ftesting"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("D|Ftesting|2027-07-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("D|Ftesting|10000-07-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2027-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|203414-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2027-07-15T15:34|200202-08-15T15:34"));
    }

    @Test
    public void parseTask_illegalDates_failure() {
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("D|F|testing|202020-07-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("D|Ftesting|2020-100-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("D|Ftesting|2020-07-2132131321T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|203414-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-420-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-07-200T15:34|2027-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-07-15T15:34|202721412-08-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-07-15T15:34|2020-800-15T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-07-15T15:34|2020-08-222323T15:34"));
        assertThrows(JukeParseException.class, () -> FileParser.parseTask("E|Ftesting|2020-08-15T15:34|2020-07-15T15:34"));
    }
}
