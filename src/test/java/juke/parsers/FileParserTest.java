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
     * Tests for the {@code parseTask} method with valid inputs combination one.
     */
    @Test
    public void parseTask_validCombinationOne_success() {
        assertEquals(new JukeTodo("testing", false).toString(),
                                FileParser.parseTask("T|F|testing").toString());
    }

    /**
     * Tests for the {@code parseTask} method with valid inputs combination two.
     */
    @Test
    public void parseTask_validCombinationTwo_success() {
        assertEquals(new JukeDeadline("testing", DateTimeParser
                             .fromDateTimeString("2027-07-15T15:34"), false).toString(),
                     FileParser.parseTask("D|F|testing|2027-07-15T15:34").toString());
    }

    /**
     * Tests for the {@code parseTask} method with valid inputs combination three.
     */
    @Test
    public void parseTask_validCombinationThree_success() {
        assertEquals(new JukeEvent(
                             "testing", DateTimeParser.fromDateTimeString("2027-07-15T15:34"),
                             DateTimeParser.fromDateTimeString("2027-08-15T15:34"), false).toString(),
                     FileParser.parseTask("E|F|testing|2027-07-15T15:34|2027-08-15T15:34").toString());
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination one.
     */
    @Test
    public void parseTask_malformedCombinationOne_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("this is obviously wrong"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination two.
     */
    @Test
    public void parseTask_malformedCombinationTwo_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("this is obviously wrong"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination three.
     */
    @Test
    public void parseTask_malformedCombinationThree_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("D|Ftesting|2027-07-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination four.
     */
    @Test
    public void parseTask_malformedCombinationFour_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask("D|Ftesting|10000-07-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination five.
     */
    @Test
    public void parseTask_malformedCombinationFive_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|2027-07-15T15:34|2027-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination six.
     */
    @Test
    public void parseTask_malformedCombinationSix_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|203414-07-15T15:34|2027-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with malformed commands combination seven.
     */
    @Test
    public void parseTask_malformedCombinationSeven_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|Ftesting|2027-07-15T15:34|200202-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination one.
     */
    @Test
    public void parseTask_illegalDatesCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask("D|F|testing|202020-07-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination two.
     */
    @Test
    public void parseTask_illegalDatesCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask("D|F|testing|2020-100-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination three.
     */
    @Test
    public void parseTask_illegalDatesCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "D|F|testing|2020-07-2132131321T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination four.
     */
    @Test
    public void parseTask_illegalDatesCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|203414-07-15T15:34|2027-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination five.
     */
    @Test
    public void parseTask_illegalDatesCombinationFive_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-420-15T15:34|2027-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination six.
     */
    @Test
    public void parseTask_illegalDatesCombinationSix_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-200T15:34|2027-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination seven.
     */
    @Test
    public void parseTask_illegalDatesCombinationSeven_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|202721412-08-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination eight.
     */
    @Test
    public void parseTask_illegalDatesCombinationEight_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|2020-800-15T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination nine.
     */
    @Test
    public void parseTask_illegalDatesCombinationNine_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-07-15T15:34|2020-08-222323T15:34"));
    }

    /**
     * Tests for the {@code parseTask} method with invalid inputs combination ten.
     */
    @Test
    public void parseTask_illegalDatesCombinationTen_failure() {
        assertThrows(JukeDataFileParseException.class, () -> FileParser.parseTask(
                "E|F|testing|2020-08-15T15:34|2020-07-15T15:34"));
    }
}
