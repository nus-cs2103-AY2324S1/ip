package rock.logic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    static final String TEST_STR = "Test String 123 /by 123312 /from 1394";
    @Test
    public void defaultStringTest() {
        assertEquals("Test String 123", new Parser(TEST_STR).getDefaultString());
    }

    @Test
    public void taggedStringTest() {
        assertEquals("123312", new Parser(TEST_STR).getTaggedInput("by"));
    }
}
