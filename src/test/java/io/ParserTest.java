package io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest{
    @Test
    public void defaultStringTest() {
        String TEST = "Test String 123/by 123312/from 1394";
        assertEquals("Test String 123", new Parser(TEST).getDefaultString());
    }

    @Test
    public void taggedStringTest() {
        String TEST = "Test String 123/by 123312/from 1394";
        assertEquals("123312", new Parser(TEST).getTaggedInput("by"));
    }
}
