package juke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import juke.exceptions.parsers.JukeDateFormatParseException;

/**
 * Tests for the DateTimeParser class.
 */
public class DateTimeParserTest {
    /**
     * Tests for the {@code isValidDate} method with valid dash inputs.
     */
    @Test
    public void isValidDate_validDash_success() {
        assertTrue(DateTimeParser.isValidDate("15-02-2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with valid slash inputs.
     */
    @Test
    public void isValidDate_validSlash_success() {
        assertTrue(DateTimeParser.isValidDate("15/02/2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with valid mixed delimiters.
     */
    @Test
    public void isValidDate_validMixed_success() {
        assertTrue(DateTimeParser.isValidDate("15/02-2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with invalid day.
     */
    @Test
    public void isValidDate_invalidDay_success() {
        assertFalse(DateTimeParser.isValidDate("32-12-2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with invalid month.
     */
    @Test
    public void isValidDate_invalidMonth_success() {
        assertFalse(DateTimeParser.isValidDate("12-14-2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with invalid year.
     */
    @Test
    public void isValidDate_invalidYear_success() {
        assertFalse(DateTimeParser.isValidDate("12-05-1000000"));
    }

    /**
     * Tests for the {@code isValidDate} method with whitespace-leading or whitespace-trailing
     * commands with dash.
     */
    @Test
    public void isValidDate_whitespacedDash_failure() {
        assertFalse(DateTimeParser.isValidDate("  15-02-2023  "));
    }

    /**
     * Tests for the {@code isValidDate} method with whitespace-leading or whitespace-trailing
     * commands with slash.
     */
    @Test
    public void isValidDate_whitespacedSlash_failure() {
        assertFalse(DateTimeParser.isValidDate("  15/02/2023  "));
    }

    /**
     * Tests for the {@code isValidDate} method with excessively whitespaced commands with dash.
     */
    @Test
    public void isValidDate_excessivelyWhitespacedDash_failure() {
        assertFalse(DateTimeParser.isValidDate("  15   -   02   -   2023  "));
    }

    /**
     * Tests for the {@code isValidDate} method with excessively whitespaced commands with slash.
     */
    @Test
    public void isValidDate_excessivelyWhitespacedSlash_failure() {
        assertFalse(DateTimeParser.isValidDate("  15   /   02   /   2023  "));
    }

    /**
     * Tests for the {@code isValidDate} method with illegal symbol "?".
     */
    @Test
    public void isValidDate_illegalSymbolsQuestionMark_failure() {
        assertFalse(DateTimeParser.isValidDate("15?02?2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with illegal symbol "|".
     */
    @Test
    public void isValidDate_illegalSymbolsPipe_failure() {
        assertFalse(DateTimeParser.isValidDate("15|02|2023"));
    }


    /**
     * Tests for the {@code isValidDate} method with malformed input with slash.
     */
    @Test
    public void isValidDate_malformedSlash_failure() {
        assertFalse(DateTimeParser.isValidDate("15002/2023"));
    }

    /**
     * Tests for the {@code isValidDate} method with malformed input with dash.
     */
    @Test
    public void isValidDate_malformedDash_failure() {
        assertFalse(DateTimeParser.isValidDate("15002-2023"));

    }

    /**
     * Tests for the {@code isValidDateTime} method with valid inputs one.
     */
    @Test
    public void isValidDateTime_normalCombinationOne_success() {
        assertTrue(DateTimeParser.isValidDateTime("15-02-2023 15-23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with valid inputs two.
     */
    @Test
    public void isValidDateTime_normalCombinationTwo_success() {
        assertTrue(DateTimeParser.isValidDateTime("15/02/2023 15-23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with valid inputs three.
     */
    @Test
    public void isValidDateTime_normalCombinationThree_success() {
        assertTrue(DateTimeParser.isValidDateTime("15-02-2023 15:23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with valid inputs four.
     */
    @Test
    public void isValidDateTime_normalCombinationFour_success() {
        assertTrue(DateTimeParser.isValidDateTime("15/02/2023 15:23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method whiteSpaced DateTimes for combination
     * one.
     */
    @Test
    public void isValidDateTime_whitespacedCombinationOne_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15-02-2023 15-23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method whiteSpaced DateTimes for combination
     * two.
     */
    @Test
    public void isValidDateTime_whitespacedCombinationTwo_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15/02/2023 15-23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method whiteSpaced DateTimes for combination
     * three.
     */
    @Test
    public void isValidDateTime_whitespacedCombinationThree_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15-02-2023 15:23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method whiteSpaced DateTimes for combination
     * four.
     */
    @Test
    public void isValidDateTime_whitespacedCombinationFour_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15/02/2023 15:23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method with excessively whitespaced DateTimes
     * with combination one.
     */
    @Test
    public void isValidDateTime_excessivelyWhitespacedCombinationOne_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15   -   02   -   2023   15   -   23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method with excessively whitespaced DateTimes
     * with combination two.
     */
    @Test
    public void isValidDateTime_excessivelyWhitespacedCombinationTwo_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15   /   02   /   2023   15   -   23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method with excessively whitespaced DateTimes
     * with combination three.
     */
    @Test
    public void isValidDateTime_excessivelyWhitespacedCombinationThree_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15   -   02   -   2023   15   :   23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method with excessively whitespaced DateTimes
     * with combination four.
     */
    @Test
    public void isValidDateTime_excessivelyWhitespacedCombinationFour_failure() {
        assertFalse(DateTimeParser.isValidDateTime("   15   /   02   /   2023   15   :   23   "));
    }

    /**
     * Tests for the {@code isValidDateTime} method with illegal symbols with combination one.
     */
    @Test
    public void isValidDateTime_illegalSymbolsCombinationOne_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15?02?2023 14?23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with illegal symbols with combination two.
     */
    @Test
    public void isValidDateTime_illegalSymbolsCombinationTwo_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15|02|2023 14|23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with illegal symbols with combination three.
     */
    @Test
    public void isValidDateTime_illegalSymbolsCombinationThree_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15?02?2023 14|23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with illegal symbols with combination four.
     */
    @Test
    public void isValidDateTime_illegalSymbolsCombinationFour_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15|02|2023 14?23"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with malformed inputs combination one.
     */
    @Test
    public void isValidDateTime_malformedCombinationOne_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15002/2023"));
    }

    /**
     * Tests for the {@code isValidDateTime} method with malformed inputs combination two.
     */
    @Test
    public void isValidDateTime_malformedCombinationTwo_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15002|2023"));

    }

    /**
     * Tests for the {@code isValidDateTime} method with malformed inputs combination three.
     */
    @Test
    public void isValidDateTime_malformedCombinationThree_failure() {
        assertFalse(DateTimeParser.isValidDateTime("15002-2023"));

    }

    /**
     * Tests for the {@code parse} method with valid Date inputs combination one.
     */
    @Test
    public void parse_validDateCombinationOne_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15-02-2023").toString());
    }

    /**
     * Tests for the {@code parse} method with valid Date inputs combination two.
     */
    @Test
    public void parse_validDateCombinationTwo_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15/02/2023").toString());
    }

    /**
     * Tests for the {@code parse} method with valid Date inputs combination three.
     */
    @Test
    public void parse_validDateCombinationThree_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15-02-2023").toString());
    }

    /**
     * Tests for the {@code parse} method with valid Date inputs combination four.
     */
    @Test
    public void parse_validDateCombinationFour_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("15/02/2023").toString());
    }

    /**
     * Tests for the {@code parse} method with valid DateTime inputs combination one.
     */
    @Test
    public void parse_validDateTimeCombinationOne_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15-02-2023 15-23").toString());
    }

    /**
     * Tests for the {@code parse} method with valid DateTime inputs combination two.
     */
    @Test
    public void parse_validDateTimeCombinationTwo_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15/02/2023 15-23").toString());
    }

    /**
     * Tests for the {@code parse} method with valid DateTime inputs combination three.
     */
    @Test
    public void parse_validDateTimeCombinationThree_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15-02-2023 15:23").toString());
    }

    /**
     * Tests for the {@code parse} method with valid DateTime inputs combination four.
     */
    @Test
    public void parse_validDateTimeCombinationFour_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("15/02/2023 15:23").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with Date combination one.
     */
    @Test
    public void parse_dateWhitespaceCombinationOne_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15-02-2023   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with Date combination two.
     */
    @Test
    public void parse_dateWhitespaceCombinationTwo_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15/02/2023   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with Date combination three.
     */
    @Test
    public void parse_dateWhitespaceCombinationThree_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15-02/2023   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with Date combination four.
     */
    @Test
    public void parse_dateWhitespaceCombinationFour_success() {
        assertEquals("2023-02-15T00:00", DateTimeParser.parse("   15/02-2023   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with DateTime combination one.
     */
    @Test
    public void parse_dateTimeWhitespaceCombinationOne_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15-02-2023 15-23   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with DateTime combination two.
     */
    @Test
    public void parse_dateTimeWhitespaceCombinationTwo_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15/02/2023 15-23   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with DateTime combination three.
     */
    @Test
    public void parse_dateTimeWhitespaceCombinationThree_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15-02-2023 15:23   ").toString());
    }

    /**
     * Tests for the {@code parse} method with whitespace-leading or trailing inputs with DateTime combination four.
     */
    @Test
    public void parse_dateTimeWhitespaceCombinationFour_success() {
        assertEquals("2023-02-15T15:23", DateTimeParser.parse("   15/02/2023 15:23   ").toString());
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced Date combination one.
     */
    @Test
    public void parse_dateExcessiveWhitespacesCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   -   02   -   2023   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced Date combination two.
     */
    @Test
    public void parse_dateExcessiveWhitespacesCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   /   02   /   2023   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced Date combination three.
     */
    @Test
    public void parse_dateExcessiveWhitespacesCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   -   02   -   2023   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced Date combination four.
     */
    @Test
    public void parse_dateExcessiveWhitespacesCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   /   02   /   2023   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced DateTime combination one.
     */
    @Test
    public void parse_dateTimeExcessiveWhitespacesCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   -   02   -   2023   15   -   23   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced DateTime combination two.
     */
    @Test
    public void parse_dateTimeExcessiveWhitespacesCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   /   02   /   2023   15   -   23   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced DateTime combination three.
     */
    @Test
    public void parse_dateTimeExcessiveWhitespacesCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   -   02   -   2023   15   :   23   "));
    }

    /**
     * Tests for the {@code parse} method with excessively whitespaced DateTime combination four.
     */
    @Test
    public void parse_dateTimeExcessiveWhitespacesCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse(
                "   15   /   02   /   2023   15   :   23   "));
    }

    /**
     * Tests for the {@code parse} method with malformed Date inputs combination one.
     */
    @Test
    public void parse_malformedDateCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002/2023"));
    }

    /**
     * Tests for the {@code parse} method with malformed Date inputs combination two.
     */
    @Test
    public void parse_malformedDateCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002-2023"));
    }

    /**
     * Tests for the {@code parse} method with malformed DateTime inputs combination one.
     */
    @Test
    public void parse_malformedDateTimeCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002/2023 14-23"));
    }

    /**
     * Tests for the {@code parse} method with malformed DateTime inputs combination two.
     */
    @Test
    public void parse_malformedDateTimeCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002/2023 14:23"));
    }

    /**
     * Tests for the {@code parse} method with malformed DateTime inputs combination three.
     */
    @Test
    public void parse_malformedDateTimeCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002-2023 14-23"));
    }

    /**
     * Tests for the {@code parse} method with malformed DateTime inputs combination four.
     */
    @Test
    public void parse_malformedDateTimeCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15002-2023 14:23"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for Date input combination one.
     */
    @Test
    public void parse_dateIllegalSymbolsCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15|02|2023"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for Date input combination two.
     */
    @Test
    public void parse_dateIllegalSymbolsCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15?02?2023"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for Date input combination three.
     */
    @Test
    public void parse_dateIllegalSymbolsCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15|02|2023"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for Date input combination four.
     */
    @Test
    public void parse_dateIllegalSymbolsCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15?02?2023"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for DateTime input combination one.
     */
    @Test
    public void parse_dateTimeIllegalSymbolsCombinationOne_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15|02|2023 14|23"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for DateTime input combination two.
     */
    @Test
    public void parse_dateTimeIllegalSymbolsCombinationTwo_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15?02?2023 14?23"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for DateTime input combination three.
     */
    @Test
    public void parse_dateTimeIllegalSymbolsCombinationThree_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15|02|2023 14?23"));
    }

    /**
     * Tests for the {@code parse} method with illegal symbols for DateTime input combination four.
     */
    @Test
    public void parse_dateTimeIllegalSymbolsCombinationFour_failure() {
        assertThrows(JukeDateFormatParseException.class, () -> DateTimeParser.parse("15?02?2023 14|23"));
    }
}
