package juke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Parser class.
 */
public class ParserTest {
    /**
     * Tests for the {@code parseBySpace} method with valid inputs.
     */
    @Test
    public void parseBySpace_valid_success() {
        String[] parsed = Parser.parseBySpace("test command");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "test");
        assertEquals(parsed[1], "command");
    }

    /**
     * Tests for the {@code parseBySpace} method with whitespace-leading or whitespace-trailing inputs.
     */
    @Test
    public void parseBySpace_withWhitespaces_success() {
        String[] parsed = Parser.parseBySpace("    tested command    ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "tested");
        assertEquals(parsed[1], "command");
    }

    /**
     * Tests for the {@code parseBySpace} method with excessively whitespaced inputs.
     */
    @Test
    public void parseBySpace_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseBySpace("    tested   command    ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "tested");
        assertEquals(parsed[1], "command");
    }

    /**
     * Tests for the {@code parseBySpace} method with malformed inputs.
     */
    @Test
    public void parseBySpace_malformed_failure() {
        String[] parsed = Parser.parseBySpace("thisshouldnotwork");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "thisshouldnotwork");
    }

    /**
     * Tests for the {@code parseByByString} method with valid inputs.
     */
    @Test
    public void parseByByString_valid_success() {
        String[] parsed = Parser.parseByByString("string /by time");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    /**
     * Tests for the {@code parseByByString} method with whitespace-leading or
     * whitespace-trailing inputs.
     */
    @Test
    public void parseByByString_withWhitespaces_success() {
        String[] parsed = Parser.parseByByString("     string /by time     ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    /**
     * Tests for the {@code parseByByString} method with excessively whitespaced inputs.
     */
    @Test
    public void parseByByString_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseByByString("     string   /by   time     ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    /**
     * Tests for the {@code parseByByString} method with malformed inputs.
     */
    @Test
    public void parseByByString_malformed_failure() {
        String[] parsed = Parser.parseByByString("string/bytime");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/bytime");
    }

    /**
     * Tests for the {@code parseByFromToString} method with valid inputs.
     */
    @Test
    public void parseByFromToString_valid_success() {
        String[] parsed = Parser.parseByFromToString("string /from now /to later");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    /**
     * Tests for the {@code parseByFromToString} method with whitespace-leading or
     * whitespace-trailing inputs.
     */
    @Test
    public void parseByFromToString_withWhitespaces_success() {
        String[] parsed = Parser.parseByFromToString("   string /from now /to later   ");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    /**
     * Tests for the {@code parseByFromToString} method with excessively whitespaced inputs.
     */
    @Test
    public void parseByFromToString_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseByFromToString("   string   /from   now   /to   later   ");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    /**
     * Tests for the {@code parseByFromToString} method with malformed inputs.
     */
    @Test
    public void parseByFromToString_malformed_failure() {
        String[] parsed = Parser.parseByFromToString("string/fromnow/tolater");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/fromnow/tolater");
    }

    /**
     * Tests for the {@code isMatchByString} method with valid input combination one.
     */
    @Test
    public void isMatchByString_validCombinationOne_success() {
        assertTrue(Parser.isMatchByString("testing /by test"));
    }

    /**
     * Tests for the {@code isMatchByString} method with valid input combination two.
     */
    @Test
    public void isMatchByString_validCombinationTwo_success() {
        assertTrue(Parser.isMatchByString("testing /by test more than one item"));
    }

    /**
     * Tests for the {@code isMatchByString} method with valid input combination three.
     */
    @Test
    public void isMatchByString_validCombinationThree_success() {
        assertTrue(Parser.isMatchByString("testing more than one argument /by test"));
    }

    /**
     * Tests for the {@code isMatchByString} method with malformed by input combination one.
     */
    @Test
    public void isMatchByString_malformedCombinationOne_failure() {
        assertFalse(Parser.isMatchByString("testing/bytest"));
    }

    /**
     * Tests for the {@code isMatchByString} method with malformed by input combination two.
     */
    @Test
    public void isMatchByString_malformedCombinationTwo_failure() {
        assertFalse(Parser.isMatchByString("testing/by test"));
    }

    /**
     * Tests for the {@code isMatchByString} method with malformed by input combination three.
     */
    @Test
    public void isMatchByString_malformedCombinationThree_failure() {
        assertFalse(Parser.isMatchByString("testing /bytest"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid input combination one.
     */
    @Test
    public void isMatchFromToString_validCombinationOne_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate /to enddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid input combination two.
     */
    @Test
    public void isMatchFromToString_validCombinationTwo_success() {
        assertTrue(Parser.isMatchFromToString("testing more than one arg /from testdate /to enddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid input combination three.
     */
    @Test
    public void isMatchFromToString_validCombinationThree_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate more than one /to enddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid input combination four.
     */
    @Test
    public void isMatchFromToString_validCombinationFour_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate /to enddate more than one"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid input combination five.
     */
    @Test
    public void isMatchFromToString_validCombinationFive_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate more than one  /to enddate more "
                                                      + "than one"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with malformed to-from input combination one.
     */
    @Test
    public void isMatchFromToString_malformedCombinationOne_failure() {
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate/toenddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with malformed to-from input combination two.
     */
    @Test
    public void isMatchFromToString_malformedCombinationTwo_failure() {
        assertFalse(Parser.isMatchFromToString("testing/from testdate/toenddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with malformed to-from input combination three.
     */
    @Test
    public void isMatchFromToString_malformedCombinationThree_failure() {
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate/to enddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with malformed to-from input combination four.
     */
    @Test
    public void isMatchFromToString_malformedCombinationFour_failure() {
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate /to enddate"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with malformed to-from input combination five.
     */
    @Test
    public void isMatchFromToString_malformedCombinationFive_failure() {
        assertFalse(Parser.isMatchFromToString("testing /from testdate/toenddate"));
    }
}
