package juke.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseSpace_normal_success() {
        String[] parsed = Parser.parseBySpace("test command");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "test");
        assertEquals(parsed[1], "command");
    }

    @Test
    public void parseSpace_withWhitespaces_success() {
        String[] parsed = Parser.parseBySpace("    tested command    ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "tested");
        assertEquals(parsed[1], "command");
    }

    @Test
    public void parseSpace_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseBySpace("    tested   command    ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "tested");
        assertEquals(parsed[1], "command");
    }

    @Test
    public void parseSpace_gibberish_success() {
        String[] parsed = Parser.parseBySpace("thisshouldnotwork");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "thisshouldnotwork");
    }

    @Test
    public void parseByString_normal_success() {
        String[] parsed = Parser.parseByByString("string /by time");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    @Test
    public void parseByString_withWhitespaces_success() {
        String[] parsed = Parser.parseByByString("     string /by time     ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    @Test
    public void parseByString_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseByByString("     string   /by   time     ");
        assertEquals(parsed.length, 2);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "time");
    }

    @Test
    public void parseByString_gibberish_success() {
        String[] parsed = Parser.parseByByString("string/bytime");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/bytime");
    }

    @Test
    public void parseByFromToString_normal_success() {
        String[] parsed = Parser.parseByFromToString("string /from now /to later");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    @Test
    public void parseByFromToString_withWhitespaces_success() {
        String[] parsed = Parser.parseByFromToString("   string /from now /to later   ");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    @Test
    public void parseByFromToString_excessivelyWhitespaced_success() {
        String[] parsed = Parser.parseByFromToString("   string   /from   now   /to   later   ");
        assertEquals(parsed.length, 3);
        assertEquals(parsed[0], "string");
        assertEquals(parsed[1], "now");
        assertEquals(parsed[2], "later");
    }

    @Test
    public void parseByFromToString_gibberish_success() {
        String[] parsed = Parser.parseByFromToString("string/fromnow/tolater");
        assertEquals(parsed.length, 1);
        assertEquals(parsed[0], "string/fromnow/tolater");
    }

    @Test
    public void matchesByString_normal_success() {
        assertTrue(Parser.isMatchByString("testing /by test"));
    }

    @Test
    public void matchesByString_gibberish_failure() {
        assertFalse(Parser.isMatchByString("testing/bytest"));
    }

    @Test
    public void matchesFromToString_normal_success() {
        assertTrue(Parser.isMatchFromToString("testing /from testdate /to enddate"));
    }

    @Test
    public void matchesFromToString_gibberish_failure() {
        assertFalse(Parser.isMatchFromToString("testing/fromtestdate/toenddate"));
    }
}
