package juke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import juke.exceptions.parsers.JukeDataFileParseException;
import juke.exceptions.parsers.JukeDateFormatParseException;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTodo;

/**
 * Tests for the FileParser class.
 */
public class FileParserTest {
    /**
     * Tests for the {@code parseTask} method with valid inputs.
     */
    @Test
    public void parseTask_normal_success() {
        Assertions.assertEquals(new JukeTodo("testing", false).toString(),
                                FileParser.parseTask("T|F|testing").toString());
        assertEquals(new JukeDeadline("testing", DateTimeParser
                             .fromParsedString("2027-07-15T15:34"), false).toString(),
                     FileParser.parseTask("D|F|testing|2027-07-15T15:34").toString());
        assertEquals(new JukeEvent(
                "testing", DateTimeParser.fromParsedString("2027-07-15T15:34"),
                DateTimeParser.fromParsedString("2027-08-15T15:34"), false).toString(),
                     FileParser.parseTask("E|F|testing|2027-07-15T15:34|2027-08-15T15:34").toString());
    }

    /**
     * Tests for the {@code parseTask} method with gibberish commands.
     */
    @Test
    public void parseTask_gibberish_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("this is obviously wrong"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("T|Ftesting"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("D|Ftesting|2027-07-15T15:34"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("D|Ftesting|10000-07-15T15:34"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|2027-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|203414-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|2027-07-15T15:34|200202-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs.
     */
    @Test
    public void parseTask_illegalDates_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask("D|F|testing|202020-07-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask("D|F|testing|2020-100-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                 "D|F|testing|2020-07-2132131321T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|203414-07-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-420-15T15:34|2027-08-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-200T15:34|2027-08-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|202721412-08-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|2020-800-15T15:34"));
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|2020-08-222323T15:34"));
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-08-15T15:34|2020-07-15T15:34"));
    }
}
