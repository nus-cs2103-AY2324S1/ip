package parser;

import enums.Command;
import exception.InvalidCommandException;
import exception.MissingArgumentException;
import exception.MissingTaskArgumentException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    public void testBlankSpaces() {
        assertThrows(MissingArgumentException.class, () -> CommandParser.getCommandArguments("mark      "));
    }
    @Test
    public void testMispeltCommand() {
        assertThrows(InvalidCommandException.class, () -> CommandParser.getCommandArguments("markus"));
    }
    @Test
    public void validMarkInput() {
        try {
            String output = CommandParser.getCommandArguments("mark 1");
            assertEquals("1", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    @Test
    public void validDeleteInput() {
        try {
            String output = CommandParser.getCommandArguments("delete 1");
            assertEquals("1", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    @Test
    public void validUnmarkInput() {
        try {
            String output = CommandParser.getCommandArguments("unmark 12");
            assertEquals("12", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void emptyInput() {
        assertThrows(InvalidCommandException.class, () -> CommandParser.getCommandArguments(""));
    }

    @Test
    public void noArguments() {
        assertThrows(MissingTaskArgumentException.class, () -> CommandParser.getCommandArguments("delete"));
    }
}
