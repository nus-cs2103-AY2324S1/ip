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
    public void parseBySpace_normal_success() {
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
     * Tests for the {@code parseBySpace} method with gibberish inputs.
     */
    @Test
    public void parseBySpace_gibberish_failure() {
        String[] parsed = Parser.parseBySpace("thisshouldnotwork");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "thisshouldnotwork");
    }

    /**
     * Tests for the {@code parseByByString} method with valid inputs.
     */
    @Test
    public void parseByByString_normal_success() {
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
     * Tests for the {@code parseByByString} method with gibberish inputs.
     */
    @Test
    public void parseByByString_gibberish_failure() {
        String[] parsed = Parser.parseByByString("string/bytime");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/bytime");
    }

    /**
     * Tests for the {@code parseByFromToString} method with valid inputs.
     */
    @Test
    public void parseByFromToString_normal_success() {
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
     * Tests for the {@code parseByFromToString} method with gibberish inputs.
     */
    @Test
    public void parseByFromToString_gibberish_failure() {
        String[] parsed = Parser.parseByFromToString("string/fromnow/tolater");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/fromnow/tolater");
    }

    /**
     * Tests for the {@code isMatchByString} method with valid inputs.
     */
    @Test
    public void isMatchByString_normal_success() {
        assertTrue(Parser.isMatchByString("testing /by test"));
        assertTrue(Parser.isMatchByString("testing /by test more than one item"));
        assertTrue(Parser.isMatchByString("testing more than one argument /by test"));
    }

    /**
     * Tests for the {@code isMatchByString} method with gibberish inputs.
     */
    @Test
    public void isMatchByString_gibberish_failure() {
        assertFalse(Parser.isMatchByString("testing/bytest"));
        assertFalse(Parser.isMatchByString("testing/by test"));
        assertFalse(Parser.isMatchByString("testing /bytest"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with valid inputs.
     */
    @Test
    public void isMatchFromToString_normal_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate /to enddate"));
        assertTrue(Parser.isMatchFromToString("testing more than one arg /from testdate /to enddate"));
        assertTrue(Parser.isMatchFromToString("testing /from testdate more than one /to enddate"));
        assertTrue(Parser.isMatchFromToString("testing /from testdate /to enddate more than one"));
        assertTrue(Parser.isMatchFromToString("testing /from testdate more than one  /to enddate more "
                                                      + "than one"));
    }

    /**
     * Tests for the {@code isMatchFromToString} method with gibberish inputs.
     */
    @Test
    public void isMatchFromToString_gibberish_failure() {
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate/toenddate"));
        assertFalse(Parser.isMatchFromToString("testing/from testdate/toenddate"));
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate/to enddate"));
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate /to enddate"));
        assertFalse(Parser.isMatchFromToString("testing /from testdate/toenddate"));
    }
}
