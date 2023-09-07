package HelperClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void testGetUserCommand() {
        Parser parser = new Parser();
        parser.processUserCommand("todo Read Book");
        assertEquals("todo", parser.getCommand());
    }

    @Test
    public void testGetTaskName() {
        Parser parser = new Parser();
        parser.processUserCommand("todo Read Book");
        assertEquals("Read Book", parser.getTaskName());
    }
}
