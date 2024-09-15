package ipbot.util.test;

import ipbot.model.Pair;
import ipbot.util.CommandArgumentException;
import ipbot.util.Parser;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testCheckIndexArg() {
        assertEquals(-1, Parser.checkIndexArg("abc", 3));
        assertEquals(-1, Parser.checkIndexArg("-1", 3));
        assertEquals(-1, Parser.checkIndexArg("0", 3));
        assertEquals(0, Parser.checkIndexArg("1", 3));
        assertEquals(1, Parser.checkIndexArg("2", 3));
        assertEquals(2, Parser.checkIndexArg("3", 3));
        assertEquals(-1, Parser.checkIndexArg("4", 3));
    }

    @Test
    public void testParseCommand() {
        Map<String, String> m0 = new HashMap<>();
        m0.put("", "");
        try {
            assertEquals(new Pair<>("", m0), Parser.parseCommand(""));
        } catch (CommandArgumentException e) {
            fail();
        }
        try {
            assertEquals(new Pair<>("asdf", m0), Parser.parseCommand("asdf"));
        } catch (CommandArgumentException e) {
            fail();
        }
        Map<String, String> m1 = new HashMap<>();
        m1.put("", "");
        m1.put("arg1", "a");
        m1.put("arg2", "");
        try {
            assertEquals(new Pair<>("asdf", m1), Parser.parseCommand("asdf  /arg1 a /arg2 "));
        } catch (CommandArgumentException e) {
            fail();
        }
//        Map<String, String> m2 = new HashMap<>();
//        m2.put("", "a");
//        m2.put("arg1", "1");
        try {
            Parser.parseCommand("asdf a /arg1 /arg1 1");
            fail();
        } catch (CommandArgumentException e) {
            assertTrue(true);
        }
    }
}
